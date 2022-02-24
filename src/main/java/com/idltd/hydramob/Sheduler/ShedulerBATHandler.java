package com.idltd.hydramob.Sheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.idltd.hydramob.quguar.export.sar.Product;
import com.idltd.hydramob.quguar.export.sar.Sar;
import com.idltd.hydramob.quguar.export.ser.Ser;
import com.idltd.hydramob.quguar.export.ser.SerList;
import com.idltd.hydramob.quguar.export.skh.Customer;
import com.idltd.hydramob.quguar.export.skh.Skh;
import com.idltd.hydramob.quguar.export.spr.Spr;
import com.idltd.hydramob.quguar.export.spr.SprHeader;
import com.idltd.hydramob.quguar.export.spr.SprItem;
import com.idltd.hydramob.quguar.export.ssl.Ssl;
import com.idltd.hydramob.quguar.export.ssl.SslHeader;
import com.idltd.hydramob.quguar.export.ssl.SslItem;
import com.idltd.hydramob.utils.filehandler.entity.LoadBat;
import com.idltd.hydramob.utils.filehandler.repository.LoadBatRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ShedulerBATHandler {

    @Value("${scheduler.bat.enable}")
    private boolean enabled;

    @Value("${scheduler.bat.outputdir}")
    private String outputdir;

    private final EntityManager entityManager;

    private final LoadBatRepository loadBatRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private final SimpleDateFormat dateFormatFilename = new SimpleDateFormat("ddMMyyyyHHmm");
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private final SimpleDateFormat dateFormatmmddyyyy = new SimpleDateFormat("MM.dd.yyyy");
    private final DecimalFormat quguarDecimalFormatter = new DecimalFormat("#0.000");

    private String client;
    private String sClient;

    public ShedulerBATHandler(EntityManager entityManager, LoadBatRepository loadBatRepository) {
        this.entityManager = entityManager;
        this.loadBatRepository = loadBatRepository;
    }

    @Scheduled(fixedRateString = "${scheduler.bat.fixedRate.in.milliseconds}")
    public void handleAll() throws ParseException {
        if (enabled) {
            List<Long> notExportedLt_partList = loadBatRepository.getnotexportedLt_part();
            for (Long lt_part : notExportedLt_partList) {
                exportToQuguar(lt_part);
            }
        }
    }

    private void export_sar(Long lt_part, LoadBatRepository loadBatRepository) {
        Sar sar = new Sar();
        List<LoadBat> loadBatList = loadBatRepository.getSarbyLt_part(lt_part);
        for (LoadBat loadBat : loadBatList) {
            //Добавляем строку в Sar файл

            Product product = new Product();
            product.setRECTYP("SAR");

            if (client.equalsIgnoreCase("BAT"))  product.setUNIT_NR("тыс.шт.");
            else   product.setUNIT_NR("шт.");

            product.setPROD_TYPE("P");
            product.setPROD_NR(sClient+"-"+loadBat.getLt_material());
            product.setPROD_NAME(loadBat.getLt_description());
            product.setIS_SERIAL("N");
            product.setPROD_EXPIRES("T");
            product.setEXPIRE_PERIOD("365");
            product.setHAS_SERIAL_NUMBER("N");
            product.setIS_SCAN_SERIAL_IN("N");
            product.setIS_SCAN_SERIAL_OUT("N");
            product.setIS_ECO_CERTIFICATE("N");
            product.setIS_CROSSDOCK_PICK_BY_LINE("N");
            product.setDEFAULT_OWNER_NR(sClient);
            product.setIS_CODE_REQ("N");
            sar.addProduct(product);
        }
        saveSarFile(sar,lt_part);
    }
    private void export_ser(Long lt_part, LoadBatRepository loadBatRepository) {
        Ser ser = new Ser();
        List<LoadBat> loadBatList = loadBatRepository.getSerbyLt_part(lt_part);
        for (LoadBat loadBat : loadBatList) {
            //Добавляем строку в Ser файл
                SerList serList = new SerList();
                serList.setRECTYP("SR");
                serList.setPRODUCT_NR(sClient+"-" + loadBat.getLt_material());
                serList.setSERIAL_NR(loadBat.getLt_batch());
                Date d;
                if (client.equalsIgnoreCase("BAT")) {
                    int week = Integer.parseInt(loadBat.getLt_batch().substring(2, 4));
                    d = new Date(120, 0, (7 * (week - 1)) - 1); //120 год от 1900 неделя начнается не с воскресенья а с понедельника поэтому -1
                } else {
                    d=new Date();
                }
                serList.setDATE_PROD(formatter.format(d));
                serList.setDATE_EXPIRE(formatter.format(d));
                serList.setONE_C_ID(sClient+"-" + loadBat.getLt_material());
                ser.addList(serList);
        }
        saveSerFile(ser,lt_part);
    }
    private void export_skh(Long lt_part, LoadBatRepository loadBatRepository) {
        Skh skh = new Skh();
        List<LoadBat> loadBatList = loadBatRepository.getSkhbyLt_part(lt_part);
        for (LoadBat loadBat : loadBatList) {
            //Добавляем строки в skh файл
            Customer customer = new Customer();
            customer.setRECTYP("SKH");
            customer.setFIRM_NR(sClient+"-"+loadBat.getLt_soldtoparty());
            customer.setNAME(loadBat.getLt_nameoftheshiptoparty());
            customer.setNAME_LONG(loadBat.getLt_nameoftheshiptoparty());
            customer.setADR_STREET("-");
            customer.setADR_CITY(loadBat.getLt_locationoftheshiptopar());
            customer.setADR_ZIPCODE("-");
            customer.setADR_POSTBOX("-");
            customer.setADR_COUNTRY_CODE("UA");
            customer.setTELEFON_NR_1("-");
            customer.setFAX_NR_1("-");
            customer.setINFO_EMAIL("-");
            customer.setCONTACT_PERS("-");
            customer.setNIP("-");
            customer.setREGON("-");
            customer.setIS_ORDERER("N");
            customer.setIS_SUPPLIER("N");
            customer.setIS_CLIENT("T");
            customer.setIS_CARRIER("N");
            skh.addCustomer(customer);
        }
        saveSkhFile(skh,lt_part);
    }
    private void export_spr(Long lt_part, LoadBatRepository loadBatRepository){
        Date currentDate = new Date();
        List<String> deliveryList = loadBatRepository.getDistDeliverybyLt_part(lt_part);
        for (String delivery : deliveryList) {
            Spr spr = new Spr();
            LoadBat header = loadBatRepository.getHeaderbyDelivery1(lt_part,delivery);
            if (header!= null) {
                //Добавляем SprHeader
                SprHeader sprHeader = new SprHeader();
                sprHeader.setRECTYP("SPR");
                sprHeader.setFIRM_NR(sClient);
                sprHeader.setORDER_NR(header.getLt_delivery());
                sprHeader.setCUSTOMER_NR(sClient);
                sprHeader.setC_ORDER_NR(header.getLt_delivery());
                sprHeader.setWH_NR("ZMartusovka01");
                sprHeader.setDATE_DELIV(dateFormat.format(currentDate));
                sprHeader.setDATE_SHIPPING(dateFormat.format(currentDate));
                sprHeader.setDATE_CREATED(dateFormat.format(currentDate));
                sprHeader.setDATE_ORDERED(dateFormat.format(currentDate));
                sprHeader.setADR_STREET("-");
                sprHeader.setADR_CITY("-");
                sprHeader.setADR_ZIPCODE("-");
                sprHeader.setADR_POSTBOX("-");
                sprHeader.setADR_COUNTRY_CODE("-");
                spr.addHeader(sprHeader);
                //Добавляем ITEM без 0 количества
//                List<LoadBat> ItemList = loadBatRepository.getItembyDelivery1(lt_part, delivery);
//                CriteriaBuilder cb = entityManager.getCriteriaBuilder();

                Query query = entityManager.createNativeQuery(
                        "select lt_part, " +
                                "lt_delivery, " +
                                "lt_material, " +
                                "lt_batch, " +
//                                "sum(substr(ltrim(LT_DELIVERYQUANTITY), 1, instr(ltrim(LT_DELIVERYQUANTITY)||'.', '.')-1)) \n" +
//                                "replace((sum(replace(LT_DELIVERYQUANTITY,',',''))),'.','*') "+
                                "LT_DELIVERYQUANTITY "+
                                "from LOAD_BAT\n" +
                                "where " +
                                "lt_part="+lt_part+
                                " and lt_delivery='"+delivery+ "'"+
                                " and lt_sheet=1 and ((lt_client in ('BAT','BAT_NGP') and lt_batch is not null ) or lt_client<>'BAT')" +
                                " order by lt_part, lt_delivery, lt_material, lt_batch");
//                                + "group by lt_part, lt_delivery, lt_material, lt_batch");

                @SuppressWarnings("unchecked")
                List<Object[]> ItemList = query.getResultList();

                int i=1;
                String vlt_part = new String();
                String vlt_delivery = new String();
                String vlt_material = new String();
                String vlt_batch = new String();
                Double s = new Double(0);
                int f=0;
                for (Object[] item : ItemList) {
                    //роверяем если батч null то для того что бы не нарваться на ошибку делаем его пустой строкой
                    if (item[3] == null) item[3]="";

                    //Добавляем SprItem
                    if (    vlt_part.equalsIgnoreCase(item[0].toString()) &&
                            vlt_delivery.equalsIgnoreCase(item[1].toString()) &&
                            vlt_material.equalsIgnoreCase(item[2].toString()) &&
                            vlt_batch.equalsIgnoreCase(item[3].toString())
                        ) {
                        s=s+new Double(item[4].toString().replace(" ","").replace(",",""));
                    } else
                    {
                       if (f!=0){
                           //записываем предыдущий
                           SprItem sprItem = new SprItem();
                           sprItem.setRECTYP("IT");
                           sprItem.setORDER_NR(vlt_delivery);
                           sprItem.setPOSITION_NR(String.valueOf(i));
                           sprItem.setPRODUCT_NR(sClient+"-" + vlt_material); //item.getLt_material()
                           sprItem.setPROD_SERIAL_NR(vlt_batch); //item.getLt_batch()
                           sprItem.setSTATUS_QUALITY("0");
                           sprItem.setBU_QUANTITY(quguarDecimalFormatter.format(s).replace(".",","));
//                           sprItem.setBU_QUANTITY(s.toString().replace(".",","));
                           sprItem.setDATE_CREATED(dateFormat.format(currentDate));
                           sprHeader.addItem(sprItem);
                           i=i+1;
                       }
                        f=1;
                        vlt_part=item[0].toString();
                        vlt_delivery=item[1].toString();
                        vlt_material=item[2].toString();
                        vlt_batch=item[3].toString();
                        s=new Double(item[4].toString().replace(" ","").replace(",",""));
                    }
                }
                if (f!=0){
                    f=1;
                    //записываем предыдущий
                    SprItem sprItem = new SprItem();
                    sprItem.setRECTYP("IT");
                    sprItem.setORDER_NR(vlt_delivery);
                    sprItem.setPOSITION_NR(String.valueOf(i));
                    sprItem.setPRODUCT_NR(sClient+"-" + vlt_material); //item.getLt_material()
                    sprItem.setPROD_SERIAL_NR(vlt_batch); //item.getLt_batch()
                    sprItem.setSTATUS_QUALITY("0");
//                    sprItem.setBU_QUANTITY(s.toString().replace(".",","));
                    sprItem.setBU_QUANTITY(quguarDecimalFormatter.format(s).replace(".",","));
                    sprItem.setDATE_CREATED(dateFormat.format(currentDate));
                    sprHeader.addItem(sprItem);
                    i=i+1;
                }

                saveSprFile(spr, header.getLt_delivery());
            }
        }

    }
    private void export_ssl(Long lt_part, LoadBatRepository loadBatRepository) throws ParseException {
        Date currentDate = new Date();
        List<String> deliveryList = loadBatRepository.getDistDeliverybyLt_part(lt_part);
        for (String delivery : deliveryList) {
            Ssl ssl = new Ssl();
            LoadBat header = loadBatRepository.getHeaderbyDelivery0(lt_part,delivery);
            if (header!= null) {
                //Добавляем SslHeader
                SslHeader sslHeader = new SslHeader();
                sslHeader.setRECTYP("HR");
                sslHeader.setFIRM_NR(sClient);
                sslHeader.setORDER_NR(header.getLt_delivery());
                sslHeader.setCUSTOMER_NR(sClient+"-"+header.getLt_soldtoparty());
                sslHeader.setC_ORDER_NR(header.getLt_delivery());
                sslHeader.setWH_NR("ZMartusovka01");
                Date pd =dateFormatmmddyyyy.parse(header.getLt_pickingdate());
                sslHeader.setDATE_DELIV(dateFormat.format(pd));
                sslHeader.setDATE_SHIPPING(dateFormat.format(pd));
                pd =dateFormatmmddyyyy.parse(header.getLt_delivdatefromto());
                sslHeader.setDATE_CREATED(dateFormat.format(pd));
                pd =dateFormatmmddyyyy.parse(header.getLt_delivdatefromto());
                sslHeader.setDATE_ORDERED(dateFormat.format(pd));
                sslHeader.setADR_CITY(header.getLt_locationoftheshiptopar());
                sslHeader.setSHIPMENT_TYPE_NR("АВТОМАТИЧЕСКАЯ");
                sslHeader.setADR_STREET("-");
                sslHeader.setADR_ZIPCODE("-");
                sslHeader.setADR_POSTBOX("-");
                sslHeader.setADR_COUNTRY_CODE("UA");
                ssl.addHeader(sslHeader);
                //Добавляем ITEM без 0 количества
                List<LoadBat> ItemList = loadBatRepository.getItembyDelivery0(lt_part, delivery);
                int i=1;
                for (LoadBat item : ItemList) {
                    //Добавляем SprItem
                    SslItem sslItem = new SslItem();
                    sslItem.setRECTYP("IT");
                    sslItem.setORDER_NR(item.getLt_delivery());
                    sslItem.setPOSITION_NR(String.valueOf(i));
                    sslItem.setPRODUCT_NR(sClient+"-" + item.getLt_material());
                    sslItem.setPROD_SERIAL_NR("");
                    sslItem.setSTATUS_QUALITY("0");
                    if (item.getLt_actualdeliveryqty()==null) sslItem.setBU_QUANTITY("0");
                    else sslItem.setBU_QUANTITY(item.getLt_actualdeliveryqty().replace(" ","").replace(".",","));
                    sslHeader.addItem(sslItem);
                    i=i+1;
                }
                saveSslFile(ssl, header.getLt_delivery());
            }
        }

    }

    private void exportToQuguar(Long lt_part) throws ParseException {

        try {
            client=loadBatRepository.getClientbyLt_part(lt_part);
            if (client.equalsIgnoreCase("BAT_NGP")) sClient="BAT";
            else sClient=client;

            export_sar(lt_part, loadBatRepository);
            export_ser(lt_part, loadBatRepository);
            export_skh(lt_part, loadBatRepository);
            export_spr(lt_part, loadBatRepository);
            export_ssl(lt_part, loadBatRepository);

//            //переделать - ставим признак что обработано
//            List<LoadBat> loadBatList = loadBatRepository.getbyLt_part(lt_part);
//            for (LoadBat loadBat : loadBatList) {
//
//                loadBat.setLt_exported(1L);
//                loadBat.setLt_qfilenumber(lt_part);
//                loadBatRepository.save(loadBat);
//            }

            //ставим флаг что закончили
            StoredProcedureQuery create_request = entityManager
                    .createStoredProcedureQuery("pkg_bat.setexport")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, lt_part)
                    .setParameter(2, 1L);
            create_request.execute();

        } catch (Exception e) {
            //ставим флаг что закончили
            StoredProcedureQuery create_request = entityManager
                    .createStoredProcedureQuery("pkg_bat.setexport")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, lt_part)
                    .setParameter(2, 3L);
            create_request.execute();
            e.printStackTrace();
        }

    }

    private void saveSprFile(Spr spr, String delivery) {
        Date currentDate = new Date();
        String deliveryforfilename=String.valueOf(delivery.hashCode());
        try {
            String name = String.format("%sSPR%s-%s.xml",outputdir,deliveryforfilename,dateFormatFilename.format(currentDate));
            File file = new File(name);
            JAXBContext jaxbContext = JAXBContext.newInstance(Spr.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");

            jaxbMarshaller.marshal(spr, file);
//            jaxbMarshaller.marshal(sar, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void saveSslFile(Ssl ssl, String delivery) {
        Date currentDate = new Date();
        String deliveryforfilename=String.valueOf(delivery.hashCode());
        try {
            String name = String.format("%sSSL%s-%s.xml",outputdir,deliveryforfilename,dateFormatFilename.format(currentDate));
            File file = new File(name);
            JAXBContext jaxbContext = JAXBContext.newInstance(Ssl.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");

            jaxbMarshaller.marshal(ssl, file);
//            jaxbMarshaller.marshal(sar, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void saveSerFile(Ser ser, Long lt_part) {
        try {
            String name = String.format("%sSER%05d.xml",outputdir,lt_part);
            File file = new File(name);
            JAXBContext jaxbContext = JAXBContext.newInstance(Ser.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");

            jaxbMarshaller.marshal(ser, file);
//            jaxbMarshaller.marshal(sar, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void saveSkhFile(Skh skh, Long lt_part) {
        try {
            String name = String.format("%sSKH%05d.xml",outputdir,lt_part);
            File file = new File(name);
            JAXBContext jaxbContext = JAXBContext.newInstance(Skh.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");

            jaxbMarshaller.marshal(skh, file);
//            jaxbMarshaller.marshal(sar, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void saveSarFile(Sar sar, Long lt_part) {
        try {
            String name = String.format("%sSAR%05d.xml",outputdir,lt_part);
            File file = new File(name);
            JAXBContext jaxbContext = JAXBContext.newInstance(Sar.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            System.setProperty("line.separator", "\r\n");

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");


            jaxbMarshaller.marshal(sar, file);
//            jaxbMarshaller.marshal(sar, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
