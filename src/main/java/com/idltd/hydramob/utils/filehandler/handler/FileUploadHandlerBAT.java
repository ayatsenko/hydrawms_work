package com.idltd.hydramob.utils.filehandler.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.multipart.MultipartFile;
import com.idltd.hydramob.utils.filehandler.FileUploadResult;
import com.idltd.hydramob.utils.filehandler.entity.FilehandlerLog;
import com.idltd.hydramob.utils.filehandler.entity.LoadBat;
import com.idltd.hydramob.utils.filehandler.exception.UnsupportedFileFormatException;
import com.idltd.hydramob.utils.filehandler.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import static com.idltd.hydramob.utils.StaticUtils.GetUserName;

//import org.apache.tika.Tika;

public class FileUploadHandlerBAT extends IFileUploadHandlerImpl {

    private final LoadBatRepository loadBatRepository;
    private final EntityManager entityManager;

    private final ObjectMapper mapper;
    private final SimpleDateFormat dateFormater;
    private final Long lt_part;

    private CellStyle cellStyle;
    private int bat_file_type;
    private final FileTypeEnum filetype;

    public FileUploadHandlerBAT(FileTypeEnum filetype, int bat_file_type, FilehandlerLog fhl, MultipartFile file, FileHandlerBufferRepository fileHandlerBufferRepository, FileHandlerLogRepository fileHandlerLogRepository, FileHandlerDetailLogRepository fileHandlerDetailLogRepository, FileHandlerAtomLogRepository fileHandlerAtomLogRepository,
                                EntityManager entityManager,
                                LoadBatRepository loadBatRepository
                                ) throws UnsupportedFileFormatException, IOException {
        super(FileTypeEnum.BAT, fhl,file, fileHandlerBufferRepository, fileHandlerLogRepository, fileHandlerDetailLogRepository,fileHandlerAtomLogRepository);
        this.bat_file_type=bat_file_type;
        this.loadBatRepository=loadBatRepository;
        this.entityManager=entityManager;
        //получаем LT_PART
        this.lt_part = ((BigDecimal) entityManager
                .createNativeQuery(
                        "select LOAD_BAT_PART_SEQ.nextval from dual"
                )
                .getSingleResult()).longValue();
        this.filetype = filetype;
        dateFormater = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        mapper = new ObjectMapper();
    }

    @Override
    public boolean validatefileformat(byte[] body) {
        //проверить формат файла - эксель или эксель 2003
//        Tika tika = new Tika();
//        String filetype = tika.detect(body);
//        return filetype.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")||
//                filetype.equalsIgnoreCase("application/vnd.ms-excel");
        boolean result;
        //проверяем формат файла
        try (InputStream inputStream = new ByteArrayInputStream(fhlb.getFhlb_Body()); HSSFWorkbook wb = new HSSFWorkbook(inputStream)) {
            result=true;
        }catch (Exception e){
            result=false;
        }
        return result;
    }

    @Override
    public void validate() throws UnsupportedFileFormatException {
        //проверяем это поддерживаемый формат файла
        if (!validatefileformat(fhlb.getFhlb_Body())) throw new UnsupportedFileFormatException(fhlb.getFhlb_Name(),FileTypeEnum.BAT);;
    }

    @Override
    public FileUploadResult upload() throws IOException {
        savelog(FileLogStatusEnum.SUCCESS, MessageFormat.format("Start parce and load file {0}", fhlb.getFhlb_Name()));


        Long successCount=0L;
        Long errorCount=0L;

        try (InputStream inputStream = new ByteArrayInputStream(fhlb.getFhlb_Body()); HSSFWorkbook wb = new HSSFWorkbook(inputStream)) {

            cellStyle = wb.createCellStyle();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("mm.dd.yyyy"));
            Sheet sheet;
            Iterator<Row> it;

            if (bat_file_type==1) { //отгрузка
                //Обрабатываем 1-й лист
                sheet = wb.getSheetAt(0);
                it = sheet.iterator();

                //Загружаем в промежуточную таблицу
                savelog(FileLogStatusEnum.SUCCESS, MessageFormat.format("Start load into LOAD_BAT table sheet 0 LT_PART={0}", lt_part));

                //пропускаем 1-ю строку
                if (it.hasNext()) {
                    Row row = it.next();
                }

                //обрабатываем следующие
                while (it.hasNext()) {
                    Row row = it.next();
                    if (processSheet0(row)) successCount += 1;
                    else errorCount += 1;
                }
            } else { //приход
                //Обрабатываем 2-й лист
                sheet = wb.getSheetAt(0);
                it = sheet.iterator();

                //Загружаем в промежуточную таблицу
                savelog(FileLogStatusEnum.SUCCESS, MessageFormat.format("Start load into LOAD_BAT table sheet 1 LT_PART={0}", lt_part));

                //пропускаем 1-ю строку
                if (it.hasNext()) {
                    Row row = it.next();
                }

                //обрабатываем следующие
                while (it.hasNext()) {
                    Row row = it.next();
                    if (processSheet1(row)) successCount += 1;
                    else errorCount += 1;
                }
            }

            savelog(FileLogStatusEnum.SUCCESS, MessageFormat.format("End load into LOAD_BAT table LT_PART={0}, Success load={1}, Error={2}", lt_part,successCount,errorCount));

            //разбираем и вставляем грузы по таблицам для импорта в Quguar
            savelog(FileLogStatusEnum.SUCCESS, MessageFormat.format("Start convert to declarations LOAD_BAT table LT_PART={0}", lt_part));

            //ставим флаг что можно формировать файлы
            StoredProcedureQuery create_request = entityManager
                    .createStoredProcedureQuery("pkg_bat.sendtoexport")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .setParameter(1, lt_part);
            create_request.execute();

            savelog(FileLogStatusEnum.SUCCESS, MessageFormat.format("End convert to declarations LOAD_BAT table LT_PART={0}", lt_part));

            savelog(FileLogStatusEnum.SUCCESS, MessageFormat.format("End parce and load file {0}", fhlb.getFhlb_Name()));
        } catch (IOException e) {
            e.printStackTrace();
            savelog(FileLogStatusEnum.ERROR, e.getMessage());
            throw e;
        }
        return new FileUploadResult(successCount,errorCount, fhl.getFhl_id());
    }

    protected boolean processSheet0(Row row) throws JsonProcessingException {
        boolean result = false;
        Cell cell;
        LoadBat loadBat = new LoadBat();
        loadBat.setLt_part(lt_part);
        loadBat.setCreate_user(GetUserName());
        loadBat.setLt_sheet(0L);
        loadBat.setLt_client(filetype.toString());

        try {
            cell=row.getCell(0); loadBat.setLt_delivery(formatter.formatCellValue(cell));
            cell=row.getCell(1); cell.setCellStyle(cellStyle); loadBat.setLt_pickingdate(formatter.formatCellValue(cell));
            cell=row.getCell(2); cell.setCellStyle(cellStyle); loadBat.setLt_delivdatefromto(formatter.formatCellValue(cell));
            cell=row.getCell(3); loadBat.setLt_purchasingdocument(formatter.formatCellValue(cell));
            cell=row.getCell(4); loadBat.setLt_deliverytype(formatter.formatCellValue(cell));
            cell=row.getCell(5); loadBat.setLt_processingstatus(formatter.formatCellValue(cell));
            cell=row.getCell(6); loadBat.setLt_plant(formatter.formatCellValue(cell));
            cell=row.getCell(7); loadBat.setLt_shippingpointreceivingpt(formatter.formatCellValue(cell));
            cell=row.getCell(8); loadBat.setLt_soldtoparty(formatter.formatCellValue(cell));
            cell=row.getCell(9); loadBat.setLt_storagelocation(formatter.formatCellValue(cell));
            cell=row.getCell(10); loadBat.setLt_locationoftheshiptopar(formatter.formatCellValue(cell));
            cell=row.getCell(11); loadBat.setLt_nameoftheshiptoparty(formatter.formatCellValue(cell));
            cell=row.getCell(12); loadBat.setLt_material(formatter.formatCellValue(cell));
            cell=row.getCell(13); loadBat.setLt_description(formatter.formatCellValue(cell));
            if (filetype==FileTypeEnum.BAT_NGP) {
                cell = row.getCell(14);
                loadBat.setLt_actualdeliveryqty(String.valueOf(cell.getNumericCellValue()*1000));
            }else {
                cell = row.getCell(14);
                loadBat.setLt_actualdeliveryqty(formatter.formatCellValue(cell));
            }
            cell=row.getCell(15); loadBat.setLt_baseunitofmeasure(formatter.formatCellValue(cell));

            loadBatRepository.save(loadBat);

            saveatomlog(FileLogStatusEnum.SUCCESS,mapper.writeValueAsString(loadBat),null);
            result = true;
        } catch (JpaSystemException e) {
            saveatomlog(FileLogStatusEnum.ERROR,mapper.writeValueAsString(loadBat), String.format("Номер строки: %d%n Ошибка: %s", row.getRowNum()+1, e.getMostSpecificCause().getLocalizedMessage()));
            result=false;
        }  catch (Exception e) {
            saveatomlog(FileLogStatusEnum.ERROR,mapper.writeValueAsString(loadBat),String.format("Номер строки: %d%n Ошибка: %s", row.getRowNum()+1, e.getLocalizedMessage()));
            result=false;
        }
        return result;
    }

    protected boolean processSheet1(Row row) throws JsonProcessingException {
        boolean result = false;
        Cell cell;
        LoadBat loadBat = new LoadBat();
        loadBat.setLt_part(lt_part);
        loadBat.setCreate_user(GetUserName());
        loadBat.setLt_sheet(1L);
        loadBat.setLt_client(filetype.toString());

        try {
            cell=row.getCell(0); loadBat.setLt_delivery(formatter.formatCellValue(cell));
            cell=row.getCell(1); loadBat.setLt_delivdatefromto(formatter.formatCellValue(cell));
            cell=row.getCell(2); loadBat.setLt_totalgdsmvtstat(formatter.formatCellValue(cell));
            cell=row.getCell(3); loadBat.setLt_material(formatter.formatCellValue(cell));
            cell=row.getCell(4); loadBat.setLt_description(formatter.formatCellValue(cell));
            cell = row.getCell(5); loadBat.setLt_deliveryquantity(formatter.formatCellValue(cell));
            cell=row.getCell(6); loadBat.setLt_plant(formatter.formatCellValue(cell));
            cell=row.getCell(7); loadBat.setLt_batch(formatter.formatCellValue(cell));
            cell=row.getCell(8); loadBat.setLt_externaldeliveryid(formatter.formatCellValue(cell));

            loadBatRepository.save(loadBat);

            saveatomlog(FileLogStatusEnum.SUCCESS,mapper.writeValueAsString(loadBat),null);
            result = true;
        } catch (JpaSystemException e) {
            saveatomlog(FileLogStatusEnum.ERROR,mapper.writeValueAsString(loadBat), String.format("Номер строки: %d%n Ошибка: %s", row.getRowNum()+1, e.getMostSpecificCause().getLocalizedMessage()));
            result=false;
        }  catch (Exception e) {
            saveatomlog(FileLogStatusEnum.ERROR,mapper.writeValueAsString(loadBat),String.format("Номер строки: %d%n Ошибка: %s", row.getRowNum()+1, e.getLocalizedMessage()));
            result=false;
        }
        return result;
    }

    @Override
    protected boolean process(Row row) throws JsonProcessingException {
        return false;
    }
}
