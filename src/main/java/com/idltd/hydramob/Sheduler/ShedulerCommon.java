package com.idltd.hydramob.Sheduler;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.idltd.hydramob.Sheduler.entity.Sheduler_Event;
import com.idltd.hydramob.entity.MailTask;
import com.idltd.hydramob.entity.Mail_List;
import com.idltd.hydramob.entity.sheduler.*;
import com.idltd.hydramob.repository.Mail_ListRepository;
import com.idltd.hydramob.repository.sheduler.*;
import com.idltd.hydramob.utils.mail.EmailServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShedulerCommon {

    private static final Logger logger = LoggerFactory.getLogger(ShedulerCommon.class);

    private final MailTaskRepository mailTaskRepository;
    private final Sheduler_EventRepository sheduler_eventRepository;
    private final FileArchRepository fileArchRepository;
    private final FinanceFileDetailRepository financeFileDetailRepository;
    private final Mail_ListRepository mail_ListRepository;
    private final ShedulerCurrencyDetailRepository shedulerCurrencyDetailRepository;
    private final ShedulerBankDetailRepository shedulerBankDetailRepository;
    private final ShedulerDKFileDetailRepository shedulerDKFileDetailRepository;

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall wmsstockresultJdbcCall;
    private boolean wmsstockresult_running;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        wmsstockresultJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_SCHEDULER")
                .withProcedureName("WH_CLIENTS_QUANTITY_LOAD");

    }

    @Scheduled(cron = "0 00 01 ? * *")
    public void wmsstockresultAll(){
        if (!wmsstockresult_running) {
            wmsstockresult_running = true;
            wmsstockresultJdbcCall.execute();
            wmsstockresult_running = false;
        }
    }

    public ShedulerCommon(
            MailTaskRepository mailTaskRepository,
            Sheduler_EventRepository sheduler_eventRepository,
            FileArchRepository fileArchRepository,
            FinanceFileDetailRepository financeFileDetailRepository,
            Mail_ListRepository mail_ListRepository,
            ShedulerCurrencyDetailRepository shedulerCurrencyDetailRepository,
            ShedulerBankDetailRepository shedulerBankDetailRepository,
            ShedulerDKFileDetailRepository shedulerDKFileDetailRepository,
            JdbcTemplate jdbcTemplate) {
        this.mailTaskRepository = mailTaskRepository;
        this.sheduler_eventRepository = sheduler_eventRepository;
        this.fileArchRepository = fileArchRepository;
        this.financeFileDetailRepository = financeFileDetailRepository;
        this.mail_ListRepository = mail_ListRepository;
        this.shedulerCurrencyDetailRepository = shedulerCurrencyDetailRepository;
        this.shedulerBankDetailRepository = shedulerBankDetailRepository;
        this.shedulerDKFileDetailRepository = shedulerDKFileDetailRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
    @Autowired
    private Environment environment;
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    //@Scheduled(fixedRate = 1800000) //30 min*/
    //@Scheduled(fixedRate = 300000) //5 min*/
    @Scheduled(fixedRateString = "${scheduler.zammler1c.fixedRate.in.milliseconds}") //2 min*/
    public void run(){
        String[] profileName = environment.getActiveProfiles();

        List<Sheduler_Event> shedulerEventList = sheduler_eventRepository.queryByStateisNull();
        if(((ArrayList) shedulerEventList).size() > 0
                && !profileName[0].equals("prod_test")
                //&& !profileName[0].equals("prod")
        ) {
            for (Sheduler_Event se : shedulerEventList) {
                try {
                    switch (se.set_id.intValue()) {
                        case 1:
                            se.se_result_detail = mailit(se);
                            se.se_state = "done";
                            break;
                        case 201:
                            se.se_result_detail = finfile1C(se);
                            se.se_state = "start";
                            break;
                        case 202:
                            CntServiceTaskExecute(se);
                            se.se_state = "done";
                            break;
                        case 203:
                            CntStatusTaskExecute(se);
                            se.se_state = "done";
                            break;
                        case 204:
                            se.se_result_detail = mailSendMail(se);
                            se.se_state = "done";
                            break;
                        case 205:
                            CurrencyParce(se);
                            se.se_state = "done";
                            break;
                        case 206:
                            se.se_result_detail = mailSendMail(se);
                            se.se_state = "done";
                            break;
                        case 207:
                            se.se_result_detail = mailSendMail(se);
                            se.se_state = "done";
                            break;
                        case 208:
                            BankParce(se);
                            se.se_state = "done";
                            break;
                        case 209:
                            FileStart(se.se_id);
                            finfileDK(se);
                            break;
                        case 212:
                            UkrPostAddressUpload();
                            se.se_state = "done";
                            break;
                        case 213:
                            se.se_result_detail = mailSendMail(se);
                            se.se_state = "done";
                            break;
                        case 214:
                            se.se_result_detail = mailSendMail(se);
                            se.se_state = "done";
                            break;
                        default:
                            throw new UnsupportedOperationException();
                    }
                } catch (Exception e) {
                    FileNotFoundError(se.se_id, e.getMessage());
                    //se.se_result_detail = e.getMessage();
                    //se.se_state = "error";
                    //StoredProcedureQuery AddQuery = entityManager.createStoredProcedureQuery("pkg_sheduler.pr_AddForexFileEventCust");
                    //AddQuery.execute();
                }
                sheduler_eventRepository.save(se);
                FileSuccess(se.se_id);
            }
        }
    }

    private String mailit(Sheduler_Event se) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        com.idltd.hydramob.Sheduler.mail.Message message = objectMapper.readValue(se.se_todo, com.idltd.hydramob.Sheduler.mail.Message.class);
        MailTask mt = new MailTask();
        mt.mt_from=message.from;
        mt.mt_to=message.to;
        mt.mt_subject=message.subject;
        mt.mt_text=message.text;
        mailTaskRepository.save(mt);
        return  "transfered into mail queue mt_id="+mt.mt_id.toString();
    }

//Сохранение сторки 1С
    public void FinanceLoadObjectList(Long sfaId, char columnseparator, InputStream csvFile) throws IOException {
        CsvMapper mapper = new CsvMapper();
        //Reader newreader = new BufferedReader(new InputStreamReader(csvFile, Charset.forName("windows-1251")));
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(columnseparator); // use first row as header; otherwise defaults are fine
        MappingIterator<ShedulerFileDetail> it =
                mapper.readerFor(ShedulerFileDetail.class)
                        .with(schema)
                        .readValues(
                                //csvFile
                                //newreader
                                new InputStreamReader(csvFile, Charset.forName("windows-1251"))
                                //inputStream
                        );
        while (it.hasNext()) {
            ShedulerFileDetail row = it.next();
            if (!row.fa_doc.isEmpty()){
                row.sfa_id=sfaId;
                financeFileDetailRepository.save(row);
            }
        }
    }
//Запись в журнал
    public void SheduleFinanceLogInsert(Long se_id, Long step_id){
        try {
            StoredProcedureQuery UploadFinQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerFinanceFileLog")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, new Long(481))
                    .setParameter(2, new Long(1))
                    .setParameter(3, se_id)
                    .setParameter(4, step_id);
            UploadFinQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }
//Создание задачи Добаления услуг
    public void CntServiceTaskInsert(Long sfaId){
        try {
        StoredProcedureQuery CntServiceTaskInsertQuery = entityManager
                .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerCNTSerCreateTask")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, new Long(481))
                .setParameter(2, new Long(1))
                .setParameter(3, sfaId);
        CntServiceTaskInsertQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }
//Обработка задачи Добаления услуг
    public void CntServiceTaskExecute(Sheduler_Event se){
        try{
        StoredProcedureQuery cntServiceTaskExecuteQuery = entityManager
                .createStoredProcedureQuery("PKG_SHEDULER.pr_FinanceFileCntSerLink")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT)
                .setParameter(1, new Long(481))
                .setParameter(2, new Long(1))
                .setParameter(3, se.se_id);
        cntServiceTaskExecuteQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

//Создание задачи Изменение статуса
    public void CntStatusTaskInsert(){
        try{
        StoredProcedureQuery CntStatusTaskInsertQuery = entityManager
                .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerCNTStatusChangeTask");
        CntStatusTaskInsertQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }
//Обработка задачи Изменение статуса
    public void CntStatusTaskExecute(Sheduler_Event se){
        try{
        StoredProcedureQuery CntStatusTaskExecuteQuery = entityManager
                .createStoredProcedureQuery("PKG_SHEDULER.pr_CntStatusChange")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT)
                .setParameter(1, new Long(481))
                .setParameter(2, new Long(1))
                .setParameter(3, se.se_id);
        CntStatusTaskExecuteQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

//Загрузка остатоков
    public void FinaceInsert(Long sfaId){
        try{
        StoredProcedureQuery UploadFinQuery = entityManager
                .createStoredProcedureQuery("PKG_SHEDULER.pr_ParsFinanceFile")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT)
                .setParameter(1, new Long(481))
                .setParameter(2, new Long(1))
                .setParameter(3, sfaId);
        UploadFinQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    private void uploadFinanceFile(Long se, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            ShedulerFileArch financeDownload = new ShedulerFileArch();
            try (InputStream inputStream = file.getInputStream()) {

                financeDownload.se_id = se;
                financeDownload.sfa_file_name = file.getOriginalFilename();
                financeDownload.sfa_file_contenttype = file.getContentType();
                financeDownload.sfa_columnseparator = ';';
                financeDownload.sfa_type_id = new Long(1);
                financeDownload.sfa_type_insert_id = new Long(1);

                StringBuilder textBuilder = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                    int c;
                    while ((c = reader.read()) != -1) {
                        textBuilder.append((char) c);
                    }
                }
                financeDownload.sfa_file_body = textBuilder.toString();
                fileArchRepository.save(financeDownload);
            }
            try (InputStream inputStream = file.getInputStream()){
                FinanceLoadObjectList(financeDownload.sfa_id, financeDownload.sfa_columnseparator, inputStream);
                SheduleFinanceLogInsert(se, new Long(3));
                FinaceInsert(financeDownload.sfa_id);
                SheduleFinanceLogInsert(se, new Long(4));
                CntServiceTaskInsert(financeDownload.sfa_id);
                CntStatusTaskInsert();
            }
        }
    }

//Сохранение сторки Debit/Credit
    public void DKLoadObjectList(Long sfaId, char columnseparator, InputStream csvFile) throws IOException {
        CsvMapper mapper = new CsvMapper();
        //Reader newreader = new BufferedReader(new InputStreamReader(csvFile, Charset.forName("windows-1251")));
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(columnseparator); // use first row as header; otherwise defaults are fine
        MappingIterator<ShedulerDKFileDetail> itDK =
                mapper.readerFor(ShedulerDKFileDetail.class)
                        .with(schema)
                        .readValues(
                                //csvFile
                                //newreader
                                new InputStreamReader(csvFile, Charset.forName("windows-1251"))
                                //inputStream
                        );
        while (itDK.hasNext()) {
            ShedulerDKFileDetail rowDK = itDK.next();
            if (!rowDK.sh_dk_currency_name.isEmpty()){
                rowDK.sfa_id=sfaId;
               // rowDK.sh_dk_order=rowDK.sh_dk_order.replace(';',',');
                shedulerDKFileDetailRepository.save(rowDK);
            }
        }
    }

//Загрузка дебиторов
    public void DKInsert(Long sfaId){
        try{
            StoredProcedureQuery UploadFinQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerDKParseTask")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, new Long(481))
                    .setParameter(2, new Long(1))
                    .setParameter(3, sfaId);
            UploadFinQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

//Загрузка результатов дебиторов
    public void DKInsertResult(Long sfaId){
        try{
            StoredProcedureQuery UploadFinQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerDKParseTaskResult")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, new Long(481))
                    .setParameter(2, new Long(1))
                    .setParameter(3, sfaId);
            UploadFinQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    private void uploadDKFile(Long se, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            ShedulerFileArch DKDownload = new ShedulerFileArch();
            try (InputStream inputStream = file.getInputStream()) {

                DKDownload.se_id = se;
                DKDownload.sfa_file_name = file.getOriginalFilename();
                DKDownload.sfa_file_contenttype = file.getContentType();
                DKDownload.sfa_columnseparator = ';';
                DKDownload.sfa_type_id = new Long(2);
                DKDownload.sfa_type_insert_id = new Long(1);

                StringBuilder textBuilder = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                    int c;
                    while ((c = reader.read()) != -1) {
                        textBuilder.append((char) c);
                    }
                }
                DKDownload.sfa_file_body = textBuilder.toString();
                fileArchRepository.save(DKDownload);
            }
            try (InputStream inputStream = file.getInputStream()){
                DKLoadObjectList(DKDownload.sfa_id, DKDownload.sfa_columnseparator, inputStream);

                DKInsert(DKDownload.sfa_id);
                DKInsertResult(DKDownload.sfa_id);
            }
        }
        else{
           FileNotFoundError(se,"Error Upload DK File");
        }
    }

    private void fileCircle (Long se, Long type_id, java.nio.file.Path s) throws IOException {
        String name = String.valueOf(s.getFileName());
        String originalFileName = s.getFileName().toString();
        String contentType = "application/vnd.ms-excel";
        byte[] content = null;
        try {
            content = Files.readAllBytes(s);
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        //MultipartFile result;
        //File result = new File(originalFileName);
        MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
        if(type_id == 1) {
            uploadFinanceFile(se, result);
            SheduleFinanceLogInsert(se, new Long(2));
        }
        else if(type_id == 2) {
            uploadDKFile(se, result);
        }
    }

    private void fileDKCircle (Long se, java.nio.file.Path s) throws IOException {
        String name = String.valueOf(s.getFileName());
        String originalFileName = s.getFileName().toString();
        String contentType = "application/vnd.ms-excel";

        Date dk_file_start_date = new Date();
        String dk_file_start_date_log = dateFormat.format(dk_file_start_date);
        System.out.println("DK File parse start: "+ dk_file_start_date_log);

        byte[] content = null;
        try {
            content = Files.readAllBytes(s);
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
        uploadDKFile(se, result);

        Date dk_file_end_date = new Date();
        String dk_file_end_date_log = dateFormat.format(dk_file_end_date);
        System.out.println("DK File parse end: "+ dk_file_end_date_log);

        /*Success*/
        FileSuccess(se);

        /*GM Parse*/
        DKGmParse(se);
        Date dk_gm_end_date = new Date();
        String dk_gm_end_date_log = dateFormat.format(dk_gm_end_date);
        System.out.println("DK GM parse end: "+ dk_gm_end_date_log);
        /*Sale Parse*/
        DKSaleParse(se);
        Date dk_sale_end_date = new Date();
        String dk_sale_end_date_log = dateFormat.format(dk_sale_end_date);
        System.out.println("DK Sale parse end: "+ dk_sale_end_date_log);
        /*OPS Parse*/
        DKOPSParse(se);
        Date dk_ops_end_date = new Date();
        String dk_ops_end_date_log = dateFormat.format(dk_ops_end_date);
        System.out.println("DK OPS parse end: "+ dk_ops_end_date_log);
    }

    private String finfile1C(Sheduler_Event se) throws IOException {
        final String[] userMessage = {""};
        Date date = new Date();

        //String FileName = "20190921";
        String FileName = new SimpleDateFormat("yyyyMMdd").format(date);
        //Files.walk(Paths.get("C:\\Temp\\fin_files\\"))
/*Test Prop*/
        Files.walk(Paths.get("/opt/files/IN/"))
/*Prod Prop*/
                //.filter(Files::isRegularFile)
                .filter(f -> f.endsWith(FileName+".csv"))
                .collect(Collectors.toList()).forEach(s-> {
            try {
                SheduleFinanceLogInsert(se.se_id, new Long(1));
                fileCircle(se.se_id, new Long(1), s);

                userMessage[0] = "Download Successfully!";
                boolean isWritable = Files.isWritable(s);
                if (isWritable) {
                    Files.delete(s);
                    userMessage[0] = userMessage[0]+" /Deleted Successfully!";
                } else {
                    userMessage[0] = userMessage[0]+" /File is not writable.";
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error:> "+e);
                FileNotFoundError(se.se_id, e.getMessage());
            }
        });
        return userMessage[0];
        /*"file downloaded/ file copy/ file delete";*/
    }

    private String finfileDK(Sheduler_Event se) throws IOException {
        final String[] userMessage = {""};
        Date date = new Date();
        String FileName = new SimpleDateFormat("yyyyMMdd").format(date);

        Files.walk(Paths.get("/opt/files/IN/"))
            /*Test Prop*/
            //Files.walk(Paths.get("C:\\Temp3"))
/*Prod Prop*/
                    .filter(Files::isRegularFile)
                    .filter(f -> f.endsWith("DK_" + FileName + ".csv"))
                    .collect(Collectors.toList()).forEach(s -> {
                try {
                    fileDKCircle(se.se_id, s);
                    userMessage[0] = "Download Successfully!";
                    boolean isWritable = Files.isWritable(s);
                    if (isWritable) {
                        Files.delete(s);
                        userMessage[0] = userMessage[0] + " /Deleted Successfully!";
                    } else {
                        userMessage[0] = userMessage[0] + " /File is not writable.";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error:> " + e);
                    FileNotFoundError(se.se_id, e.getMessage());
                }
            });
            return userMessage[0];
        }

    @Autowired
    private EmailServiceImpl javaMailSender;

    private String mailSendMail(Sheduler_Event se) throws IOException {
        List<Mail_List> mail_List;
        final String[] userMessage = {""};
        int i = 0;

        mail_List =  mail_ListRepository.queryRequstAndTenderMail(se.se_id);
        if(mail_List.size() > 0) {
            do {
                javaMailSender.sendSimpleMessage(mail_List.get(i).user_mail, mail_List.get(i).mail_subject, mail_List.get(i).mail_text);
                i++;
            }
            while (i < mail_List.size());
        }
        userMessage[0] = "Send Successfully!";
        return userMessage[0];
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            //JSONObject json = new JSONObject(jsonText.replace("[", "").replace("]", ""));
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public void CurrencyParce(Sheduler_Event se) throws IOException, JSONException{
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        //String currentDate = "11.06.2020";
        JSONObject json = readJsonFromUrl("https://api.privatbank.ua/p24api/exchange_rates?json&date="+currentDate);
        //System.out.println(json.toString());
        JSONArray arr = json.getJSONArray("exchangeRate");
        int i = 1;
        while (i < arr.length()) {
            ShedulerCurrencyDetail shedulerCurrencyDetailRow = new ShedulerCurrencyDetail();

            String baseCurrency = arr.getJSONObject(i).getString("baseCurrency");
            String currency = arr.getJSONObject(i).getString("currency");
            Number saleRateNB = (Number) arr.getJSONObject(i).get("saleRateNB");
            String saleRateNBStr = saleRateNB.toString();
            //System.out.println(currency+": "+saleRateNB);

            shedulerCurrencyDetailRow.currency_date_name = currentDate;
            shedulerCurrencyDetailRow.currency_base_name = baseCurrency;
            shedulerCurrencyDetailRow.currency_rate_name = currency;
            shedulerCurrencyDetailRow.currency_rate_value_name = saleRateNBStr;
            shedulerCurrencyDetailRepository.save(shedulerCurrencyDetailRow);
            i++;
        }
        AddCurrencyQuery(currentDate);
    }

    public void AddCurrencyQuery(String CurrencyDate){
        try {
            StoredProcedureQuery AddCurrencyQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_AddCurrencyRate")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .setParameter(1, CurrencyDate);
            AddCurrencyQuery.execute();
        }
        catch (Exception e) {
                System.out.println("Error:> "+e);
                e.printStackTrace();
            }
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            //return buffer.toString();
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public void AddBankQuery(Long seID){
        try {
            StoredProcedureQuery AddBankQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerBankLoad")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .setParameter(1, seID)
                    ;
            AddBankQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void BankParce(Sheduler_Event se) throws IOException, JSONException{
        try {
        //JSONObject json = readJsonFromUrl("https://bank.gov.ua/NBU_BankInfo/get_data_branch?typ=0&json");
        String result = readUrl("https://bank.gov.ua/NBU_BankInfo/get_data_branch?typ=0&json");
        JSONObject json = new JSONObject("{\"result\":"+result+"}");
        JSONArray jsonarray = json.getJSONArray("result");
        //System.out.println(jsonarray);
        int i = 0;
            while (i < jsonarray.length()) {
                ShedulerBankDetail ShedulerBankDetailRow = new ShedulerBankDetail();
                ShedulerBankDetailRow.se_id = se.se_id;
                ShedulerBankDetailRow.row_id = Long.valueOf(i + 1);
                ShedulerBankDetailRow.main_mfo = Long.valueOf(jsonarray.getJSONObject(i).get("GLMFO").toString());
                ShedulerBankDetailRow.main_name = jsonarray.getJSONObject(i).getString("N_GOL");
                ShedulerBankDetailRow.company_name = jsonarray.getJSONObject(i).getString("NAME_E");
                ShedulerBankDetailRow.bank_edrpou  = Long.valueOf(jsonarray.getJSONObject(i).getString("KOD_EDRPOU"));
                ShedulerBankDetailRow.bank_sname = jsonarray.getJSONObject(i).getString("SHORTNAME");
                ShedulerBankDetailRow.bank_nkb = jsonarray.getJSONObject(i).getString("NKB");
                ShedulerBankDetailRow.bank_type = Long.valueOf(jsonarray.getJSONObject(i).getString("TYP"));
                ShedulerBankDetailRow.bank_ku = Long.valueOf(jsonarray.getJSONObject(i).getString("KU"));
                ShedulerBankDetailRow.bank_obl_name = jsonarray.getJSONObject(i).getString("N_OBL");
                ShedulerBankDetailRow.bank_depcode = jsonarray.getJSONObject(i).getString("DEPCODE");
                ShedulerBankDetailRow.bank_zipcode = jsonarray.getJSONObject(i).getString("P_IND");
                ShedulerBankDetailRow.bank_city_type = jsonarray.getJSONObject(i).getString("TNP");
                ShedulerBankDetailRow.bank_city_name = jsonarray.getJSONObject(i).getString("NP");
                ShedulerBankDetailRow.bank_adress = jsonarray.getJSONObject(i).getString("ADRESS");
                ShedulerBankDetailRow.bank_phone_code = jsonarray.getJSONObject(i).isNull("KODT") ? "" : jsonarray.getJSONObject(i).getString("KODT");
                ShedulerBankDetailRow.bank_phone = jsonarray.getJSONObject(i).isNull("TELEFON") ? "" : jsonarray.getJSONObject(i).getString("TELEFON");
                ShedulerBankDetailRow.bank_kstan = Long.valueOf(jsonarray.getJSONObject(i).getString("KSTAN"));
                ShedulerBankDetailRow.bank_nstan = jsonarray.getJSONObject(i).getString("N_STAN");
                ShedulerBankDetailRow.bank_dstan = jsonarray.getJSONObject(i).isNull("D_STAN") ? "" : jsonarray.getJSONObject(i).getString("D_STAN");
                ShedulerBankDetailRow.bank_open_date = jsonarray.getJSONObject(i).getString("D_OPEN");
                ShedulerBankDetailRow.bank_close_date = jsonarray.getJSONObject(i).isNull("D_CLOSE") ? "" : jsonarray.getJSONObject(i).getString("D_CLOSE");
                ShedulerBankDetailRow.main_stan = jsonarray.getJSONObject(i).getString("STAN_GOL");
                ShedulerBankDetailRow.main_nstan = jsonarray.getJSONObject(i).getString("NSTAN_GOL");
                ShedulerBankDetailRow.bank_mfo = Long.valueOf(jsonarray.getJSONObject(i).get("MFO").toString());
                shedulerBankDetailRepository.save(ShedulerBankDetailRow);
                i++;
            }
            AddBankQuery(se.se_id);
        }
            catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void UkrPostAddressUpload(){
        try {
            StoredProcedureQuery UkrPostUploadQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerUkrPostUpload")
                    ;
            UkrPostUploadQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void FileStart(Long seID){
        try {
            StoredProcedureQuery FileStartrQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_FileStart")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .setParameter(1, seID)
                    ;
            FileStartrQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void FileNotFoundError(Long seID, String ErrorMsg){
        try {
            StoredProcedureQuery FileNotFoundErrorQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_FileNotFoundError")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .setParameter(1, seID)
                    .setParameter(2, ErrorMsg)
                    ;
            FileNotFoundErrorQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void FileSuccess(Long seID){
        try {
            StoredProcedureQuery FileSuccessQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_FileSuccess")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .setParameter(1, seID)
                    ;
            FileSuccessQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void DKGmParse(Long seID){
        try {
            StoredProcedureQuery FileSuccessQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerDKParseAllGMUsers")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, new Long(801))
                    .setParameter(2, new Long(1))
                    .setParameter(3, seID)
                    ;
            FileSuccessQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void DKSaleParse(Long seID){
        try {
            StoredProcedureQuery FileSuccessQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerDKParseAllSaleUsers")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, new Long(801))
                    .setParameter(2, new Long(1))
                    .setParameter(3, seID)
                    ;
            FileSuccessQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }

    public void DKOPSParse(Long seID){
        try {
            StoredProcedureQuery FileSuccessQuery = entityManager
                    .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerDKParseAllOPSUsers")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, new Long(801))
                    .setParameter(2, new Long(1))
                    .setParameter(3, seID)
                    ;
            FileSuccessQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
    }
}
