package com.idltd.hydramob.controller.wms_oper_docs;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_sale_docs.DetailAllPrintRepository;
import com.idltd.hydramob.repository.wms_oper_docs.MenuWMSOperDocsDetailRepository;
import com.idltd.hydramob.repository.wms_oper_docs.MenuWMSOperDocsMainRepository;
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
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.idltd.hydramob.utils.StaticUtils.ConvertTraceExceptionToText;
import static com.idltd.hydramob.utils.StaticUtils.GetUserName;

@RestController
@RequestMapping("/wms_oper_docs")
public class WMSOperDocsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuWMSOperDocsMainRepository menuWMSOperDocsMainRepository;

    private MenuWMSOperDocsDetailRepository menuWMSOperDocsDetailRepository;

    private DetailAllPrintRepository detailAllPrintRepository;

    public WMSOperDocsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuWMSOperDocsMainRepository menuWMSOperDocsMainRepository,

            MenuWMSOperDocsDetailRepository menuWMSOperDocsDetailRepository,

            DetailAllPrintRepository detailAllPrintRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuWMSOperDocsMainRepository = menuWMSOperDocsMainRepository;
        this.menuWMSOperDocsDetailRepository = menuWMSOperDocsDetailRepository;
        this.detailAllPrintRepository = detailAllPrintRepository;
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
        List<User_Report_List> user_Report_List;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Client_Report_List> client_Report_List;
        List<Action_Type_List> action_Type_List;
        List<Action_Status_List> action_Status_List;
        List<Action_Result_List> action_Result_List;
        List<ActionPlanTypeList> actionPlanType;

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

         model.setViewName("wms_oper_docs/cover");
        return model;
    }

    @PostMapping("/get_menu")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuWMSOperDocsMainRepository.queryWMSOperDocsMainByID(
                user_List.get(0).id,
                user_Role_List.get(0).role_id
        ));

        return result;
    }

    @PostMapping("/add_main_doc")
    public ModelAndView add_main_doc(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_AddExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/check_main_doc")
    public ModelAndView check_main_doc(
            @RequestParam(name = "doc_id", required = false) Long doc_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_CheckExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, doc_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/uncheck_main_doc")
    public ModelAndView uncheck_main_doc(
            @RequestParam(name = "doc_id", required = false) Long doc_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_UnCheckExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, doc_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/add_new_post_to_doc")
    public ResponseEntity<?> add_new_post_to_doc(
            @RequestParam(name = "doc_id") Long doc_id,
            @RequestParam(name = "post_id") String post_id,
            @RequestParam(name = "api_key") String api_key
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        System.out.println("doc_id:> "+doc_id);
        System.out.println("post_id:> "+post_id);
        System.out.println("api_key:> "+api_key);

        Integer ErrorID = 0;
        String ErrorValue = new String();

        ResponseEntity<?> error_result;
        try{
            StoredProcedureQuery AddQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_AddPostExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.OUT)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, doc_id)
                    .setParameter(4, post_id)
                    ;
            AddQuery.execute();

            ErrorID = (Integer) AddQuery.getOutputParameterValue(5);
            ErrorValue = (String) AddQuery.getOutputParameterValue(6);

            if(ErrorID == 0){
                System.out.println("Done: ");
                error_result = new ResponseEntity<>("Посылка загружена", HttpStatus.OK);

                String FILE_URL = "https://my.novaposhta.ua/orders/printMarkings/orders/"+post_id+"/type/pdf/apiKey/"+api_key+"";
                //String FILE_NAME = "opt/files/np_files/test_"+post_id+".pdf";
                String FILE_NAME = "C:\\WMS\\test_"+post_id+".pdf";

                try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
                    byte dataBuffer[] = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    System.out.println("File load Error: ");
                    // handle exception
                }

                //это список доступных принтеров (для вібиралочки под пользователя)
                PrintService[] printServiceAll= PrintServiceLookup.lookupPrintServices(null,null);
                for (PrintService printService: printServiceAll
                     ) {
                    System.out.println(printService.getName());
                }

                PDDocument document = PDDocument.load(new File(FILE_NAME));

                 //вот сюда надо выбрать принтер в зависимости от зарегистрированного пользователя
                                                        // т.е. к таблице пользователей добавь поле принтер варчар2 а тут
                String userName = GetUserName(); //это имя пользователя под которым он зашел в систему
                String printerName;
                if (userName.equalsIgnoreCase("ayatsenko")) printerName = "Godex ZX1200i";
                else if (userName.equalsIgnoreCase("admin")) printerName = "ZDesigner ZM400 200 dpi (ZPL)";
                else if (userName.equalsIgnoreCase("wh_user")) printerName = "ZDesigner ZM400 200 dpi (ZPL)";
                else printerName="";

                if(printerName != null && !printerName.equals("")) {
                    PrintService myPrintService = findPrintService(printerName);

                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(new PDFPageable(document));
                    job.setPrintService(myPrintService);
                    job.print();
                }
                //document.close();
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

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    @PostMapping("/get_detail_menu")
    public JSONDatatable get_detail_menu(
            @RequestParam(name = "doc_id") Long doc_id
            ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        if (doc_id != null && doc_id > 0){
            result.setData(menuWMSOperDocsDetailRepository.queryWMSOperDocsDetailByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    doc_id
            ));
        }
        return result;
    }

    @PostMapping("/del_detail_doc")
    public ModelAndView del_detail_doc(
            @RequestParam(name = "doc_link_id", required = false) Long doc_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_DelPostExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, doc_link_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/get_all_print_detail")
    public JSONDatatable get_all_print_detail(
            @RequestParam(name="doc_id", defaultValue = "0") Long doc_id,
            @RequestParam(name="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(doc_id != null && doc_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailAllPrintRepository.queryDetailAllPrintByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, doc_id
            ));
        }
        return result;
    }
}
