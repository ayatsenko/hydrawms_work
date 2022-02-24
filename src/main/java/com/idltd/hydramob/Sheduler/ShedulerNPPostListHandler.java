package com.idltd.hydramob.Sheduler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idltd.hydramob.Sheduler.NP.*;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.wms_list.WMSUsersPrintersList;
import com.idltd.hydramob.entity.wms_sale_docs.MenuWMSSaleDocsDetail;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_list.WMSUsersPrintersListRepository;
import com.idltd.hydramob.repository.wms_sale_docs.MenuWMSSaleDocsDetailRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.idltd.hydramob.utils.StaticUtils.ConvertObjectToJson;

@Component
@RequestMapping("/np_post")
public class ShedulerNPPostListHandler {

    private String uri = "https://api.novaposhta.ua/v2.0/json";

    private ObjectMapper mapper;

    @Value("${scheduler.np_post_list.enable}")
    private boolean enabled;

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuWMSSaleDocsDetailRepository menuWMSSaleDocsDetailRepository;
    private WMSUsersPrintersListRepository wmsUsersPrintersListRepository;

    public ShedulerNPPostListHandler(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuWMSSaleDocsDetailRepository menuWMSSaleDocsDetailRepository,
            WMSUsersPrintersListRepository wmsUsersPrintersListRepository
    ) {
        super();
        this.mapper = new ObjectMapper();
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuWMSSaleDocsDetailRepository = menuWMSSaleDocsDetailRepository;
        this.wmsUsersPrintersListRepository = wmsUsersPrintersListRepository;
    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    //Отправка запроса в НП
    public NPResponse send(Object np_request) {

        String query=String.format("%s/",uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity= new HttpEntity<>(np_request, headers);

        NPResponse np_responseBody = null;
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<NPResponse> response = restTemplate.exchange(query, HttpMethod.POST, httpEntity, NPResponse.class);
        np_responseBody = response.getBody();

        return np_responseBody;
    }

    //Отправка запроса в НП
    private NPResponse requestNP(String apiKey, String modelName, String calledMethod, NPMethodProperties methodProperties) throws NPResponseException {
        NPRequest counterpartyRequest = new NPRequest(apiKey, modelName, calledMethod, methodProperties );
        NPResponse npResponse = send(counterpartyRequest);
        if (!npResponse.isSuccess()) {
            throw new NPResponseException(npResponse);
        }
        return npResponse;
    }

    //Отправка запроса в НП
    public NPResponse_dss send_dss(Object np_request) {

        String query=String.format("%s/",uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity= new HttpEntity<>(np_request, headers);

        NPResponse_dss np_responseBody = null;
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<NPResponse_dss> response = restTemplate.exchange(query, HttpMethod.POST, httpEntity, NPResponse_dss.class);
        np_responseBody = response.getBody();

        return np_responseBody;
    }

    //Отправка запроса в НП
    private NPResponse_dss requestNP_dss(String apiKey, String modelName, String calledMethod, NPMethodProperties methodProperties) throws Exception {
        NPRequest counterpartyRequest = new NPRequest(apiKey, modelName, calledMethod, methodProperties );
        String s = ConvertObjectToJson(counterpartyRequest);
        System.out.println(s);
        NPResponse_dss npResponse = send_dss(counterpartyRequest);
        if (!npResponse.isSuccess()) {
            throw new Exception(ConvertObjectToJson(npResponse));
        }
        return npResponse;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/check_main_doc")
    public ModelAndView check_main_doc(
            @RequestParam(name = "doc_id", required = false) Long doc_id,
            @RequestParam(name = "api_key", required = false) String api_key
    ) {
        NPResponse npResponse;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<MenuWMSSaleDocsDetail> MenuWMSSaleDocsDetail;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery RefNullFillActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_addPostNPRefNullFill")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id);
            RefNullFillActQuery.execute();

            //создаем реестр Новой почты
            List<String> documentRefs = new ArrayList<>();

            MenuWMSSaleDocsDetail = menuWMSSaleDocsDetailRepository.queryWMSSaleDocsDetailByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    doc_id
            );

            int i = 0;
            int ref_list_end = MenuWMSSaleDocsDetail.size();

            while (i < ref_list_end) {
                documentRefs.add(MenuWMSSaleDocsDetail.get(i).client_post_ref);
                i++;
            }

            npResponse = requestNP(api_key, "ScanSheet", "insertDocuments", new NPScanSheetRequestPropertyDTO(documentRefs));
            List<NPScanSheetDTO> npScanSheetDTOList = mapper.convertValue(npResponse.getData(), new TypeReference<List<NPScanSheetDTO>>(){});
            List<String> scanSheetRefs = new ArrayList<>();

            for (NPScanSheetDTO npScanSheetDTO : npScanSheetDTOList) {
                scanSheetRefs.add(npScanSheetDTO.getRef());

                if (npScanSheetDTO.getRef() != null && !npScanSheetDTO.getRef().equals("")) {
                    StoredProcedureQuery DelActQuery = entityManager
                            .createStoredProcedureQuery("PKG_WMS.pr_CheckExchangeSaleDoc")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, doc_id)
                            .setParameter(4, npScanSheetDTO.getRef())
                            .setParameter(5, npScanSheetDTO.getNumber());
                    DelActQuery.execute();

                    //это список доступных принтеров (для вібиралочки под пользователя)
                    PrintService[] printServiceAll= PrintServiceLookup.lookupPrintServices(null,null);
                    for (PrintService printService: printServiceAll
                    ) {
                        System.out.println(printService.getName());
                    }

                    List<WMSUsersPrintersList> UsersPrintersList =
                            wmsUsersPrintersListRepository.queryUserPrinterPDFDefaultList(user_List.get(0).id, user_Role_List.get(0).role_id);

                    if(UsersPrintersList.get(0).printer_name != null) {
                        PrintService myPrintService = findPrintService(UsersPrintersList.get(0).printer_name);

                        if(myPrintService != null) {
                            String FILE_URL = "https://my.novaposhta.ua/orders/printMarkings/orders/"+npScanSheetDTO.getRef()+"/type/pdf/apiKey/"+api_key+"";
                            //String FILE_NAME = "opt/files/np_files/test_"+post_id+".pdf";
                            String FILE_NAME = "C:\\WMS\\reestr_"+npScanSheetDTO.getRef()+".pdf";

                            try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
                                 FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
                                byte dataBuffer[] = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                                }
                            } catch (IOException e) {
                                // handle exception
                            }

                            PDDocument document = PDDocument.load(new File(FILE_NAME));

                            PrinterJob job = PrinterJob.getPrinterJob();
                            job.setPageable(new PDFPageable(document));
                            job.setPrintService(myPrintService);
                            job.print();
                            document.close();
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/uncheck_main_doc")
    public ModelAndView uncheck_main_doc(
            @RequestParam(name = "doc_id", required = false) Long doc_id,
            @RequestParam(name = "post_id", required = false) String post_id,
            @RequestParam(name = "api_key", required = false) String api_key
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        List<String> scanSheetRefs = new ArrayList<>();
        scanSheetRefs.add(post_id);

        String s = ConvertObjectToJson(new NPScanSheetDeleteRequestPropertyDTO(scanSheetRefs));
        System.out.println(s);
        NPResponse_dss npResponse_dss=null;
        try {
            npResponse_dss = requestNP_dss(api_key, "ScanSheet", "deleteScanSheet", new NPScanSheetDeleteRequestPropertyDTO(scanSheetRefs));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List<NPScanSheetRefsDTO> npScanSheetRefsDTOList = mapper.convertValue(npResponse_dss.getData(), new TypeReference<List<NPScanSheetRefsDTO>>(){});
        if (npResponse_dss.isSuccess()) {

            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_UnCheckExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, doc_id);
            DelActQuery.execute();
        }
        return null;
    }
}
