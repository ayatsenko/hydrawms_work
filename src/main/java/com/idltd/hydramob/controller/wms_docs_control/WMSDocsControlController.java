package com.idltd.hydramob.controller.wms_docs_control;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.wms_list.WMSUsersPrintersList;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_docs_control.DetailWMSDocsControlDocCheckRepository;
import com.idltd.hydramob.repository.wms_docs_control.MenuWMSDocsControlDocRepository;
import com.idltd.hydramob.repository.wms_docs_control.MenuWMSDocsControlPostMainRepository;
import com.idltd.hydramob.repository.wms_list.WMSUsersPrintersListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.idltd.hydramob.utils.StaticUtils.ConvertTraceExceptionToText;

@RestController
@RequestMapping("/wms_docs_control")
public class WMSDocsControlController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuWMSDocsControlDocRepository menuWMSDocsControlDocRepository;

    private MenuWMSDocsControlPostMainRepository menuWMSDocsControlPostMainRepository;
    private DetailWMSDocsControlDocCheckRepository detailWMSDocsControlDocCheckRepository;
    private WMSUsersPrintersListRepository wmsUsersPrintersListRepository;

    public WMSDocsControlController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuWMSDocsControlDocRepository menuWMSDocsControlDocRepository,

            MenuWMSDocsControlPostMainRepository menuWMSDocsControlPostMainRepository,
            DetailWMSDocsControlDocCheckRepository detailWMSDocsControlDocCheckRepository,
            WMSUsersPrintersListRepository wmsUsersPrintersListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuWMSDocsControlDocRepository = menuWMSDocsControlDocRepository;

        this.menuWMSDocsControlPostMainRepository = menuWMSDocsControlPostMainRepository;
        this.detailWMSDocsControlDocCheckRepository = detailWMSDocsControlDocCheckRepository;
        this.wmsUsersPrintersListRepository = wmsUsersPrintersListRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "act_id", required = false) Long act_id,

                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page
    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.addObject("act_id", act_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

         model.setViewName("wms_docs_control/cover");
        return model;
    }

    @PostMapping("/add_new_post_to_doc")
    public ResponseEntity<?> add_new_post_to_doc(
            @RequestParam(name = "post_id") String post_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        Integer ErrorID = 0;
        String ErrorValue = new String();

        ResponseEntity<?> error_result;
        try {
            StoredProcedureQuery AddQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_ConrolPostExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.OUT)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, post_id);
            AddQuery.execute();

            ErrorID = (Integer) AddQuery.getOutputParameterValue(4);
            ErrorValue = (String) AddQuery.getOutputParameterValue(5);

            if (ErrorID == 0) {
                error_result = new ResponseEntity<>("Посылка загружена", HttpStatus.OK);
            }
            else {
                error_result = new ResponseEntity<>(ErrorValue, HttpStatus.CONFLICT);
            }
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
            error_result = new ResponseEntity<>(ConvertTraceExceptionToText(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return error_result;
    }

    @PostMapping("/get_doc_menu")
    public JSONDatatable get_doc_menu(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuWMSDocsControlDocRepository.queryWMSDocsControlDocByID(
                user_List.get(0).id,
                user_Role_List.get(0).role_id
        ));

        return result;
    }

    @PostMapping("/get_post_menu")
    public JSONDatatable get_post_menu(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuWMSDocsControlPostMainRepository.queryWMSSaleDocsControlPostByID(
                user_List.get(0).id,
                user_Role_List.get(0).role_id
        ));

        return result;
    }

    @GetMapping("/get_doc_check")
    public JSONDatatable get_doc_check(
            @RequestParam(name="client_post_id") String client_post_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(client_post_id != null && !client_post_id.equals("")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailWMSDocsControlDocCheckRepository.queryWMSDocsControlDocCheckByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, client_post_id
            ));
        }
        return result;
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

    @PostMapping("/get_post_print")
    public JSONDatatable get_post_print(
            @RequestParam(name="post_id") Long post_id,
            @RequestParam(name="post_doc_id") String post_doc_id
    ) throws IOException {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        //String test_id = "dbfff4db-8644-11eb-8513-b88303659df5";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddQuery = entityManager
                .createStoredProcedureQuery("PKG_WMS.pr_ConrolPostDocExchangeSaleDoc")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.OUT)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, post_id);
        AddQuery.execute();

        try {
            String FILE_URL = "https://my.novaposhta.ua/scanSheet/printScanSheet/refs[]/"+ post_doc_id +"/type/pdf/apiKey/df71731dbc3928d2b31187e539747523";
            //String FILE_NAME = "opt/files/np_files/test_"+post_id+".pdf";
            String FILE_NAME = "C:\\WMS\\control_doc_" + post_id + ".pdf";

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

            //это список доступных принтеров (для вібиралочки под пользователя)
            PrintService[] printServiceAll = PrintServiceLookup.lookupPrintServices(null, null);
            for (PrintService printService : printServiceAll
            ) {
                System.out.println(printService.getName());
            }

            PDDocument document = PDDocument.load(new File(FILE_NAME));

            //вот сюда надо выбрать принтер в зависимости от зарегистрированного пользователя
            // т.е. к таблице пользователей добавь поле принтер варчар2 а тут

            List<WMSUsersPrintersList> UsersPrintersList =
                    wmsUsersPrintersListRepository.queryUserPrinterPDFDefaultList(user_List.get(0).id, user_Role_List.get(0).role_id);

            if (UsersPrintersList.get(0).printer_name != null) {
                PrintService myPrintService = findPrintService(UsersPrintersList.get(0).printer_name);

                if (myPrintService != null) {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(new PDFPageable(document));
                    job.setPrintService(myPrintService);
                    job.print();
                    document.close();
                }
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }

        return null;
    }
}
