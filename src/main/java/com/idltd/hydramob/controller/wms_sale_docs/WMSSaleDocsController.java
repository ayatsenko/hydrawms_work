package com.idltd.hydramob.controller.wms_sale_docs;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import com.idltd.hydramob.entity.wms_list.WMSUsersPrintersList;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_list.WMSUsersPrintersListRepository;
import com.idltd.hydramob.repository.wms_sale_docs.*;
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
@RequestMapping("/wms_sale_docs")
public class WMSSaleDocsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuWMSSaleDocsMainRepository menuWMSSaleDocsMainRepository;

    private MenuWMSSaleDocsDetailRepository menuWMSSaleDocsDetailRepository;

    private DetailAllPrintRepository detailAllPrintRepository;
    private DetailAllNotPrintRepository detailAllNotPrintRepository;

    private WMSUsersPrintersListRepository wmsUsersPrintersListRepository;
    private PostCountRepository postCountRepository;
    private NPClientApiKeyRepository npClientApiKeyRepository;

    public WMSSaleDocsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuWMSSaleDocsMainRepository menuWMSSaleDocsMainRepository,

            MenuWMSSaleDocsDetailRepository menuWMSSaleDocsDetailRepository,

            DetailAllPrintRepository detailAllPrintRepository,
            DetailAllNotPrintRepository detailAllNotPrintRepository,

            WMSUsersPrintersListRepository wmsUsersPrintersListRepository,
            PostCountRepository postCountRepository,
            NPClientApiKeyRepository npClientApiKeyRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuWMSSaleDocsMainRepository = menuWMSSaleDocsMainRepository;
        this.menuWMSSaleDocsDetailRepository = menuWMSSaleDocsDetailRepository;

        this.detailAllPrintRepository = detailAllPrintRepository;
        this.detailAllNotPrintRepository = detailAllNotPrintRepository;

        this.wmsUsersPrintersListRepository = wmsUsersPrintersListRepository;
        this.postCountRepository = postCountRepository;
        this.npClientApiKeyRepository = npClientApiKeyRepository;
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

         model.setViewName("wms_sale_docs/cover");
        return model;
    }

    @GetMapping("/get_client_api_key")
    public JSONDatatable get_client_api_key(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(npClientApiKeyRepository.queryGetNPClientApiKeyByUserID(
                user_List.get(0).id,user_Role_List.get(0).role_id
        ));

        return result;
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

        result.setData(menuWMSSaleDocsMainRepository.queryWMSSaleDocsMainByID(
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

    void printPost(
            String print_post_id, String print_api_key, Long print_user_id, Long print_role_id
    ){
        Integer PrintErrorID = 0;
        String PrintErrorValue = new String();
        try {
            String FILE_URL = "https://my.novaposhta.ua/orders/printMarkings/orders/" + print_post_id + "/type/pdf/apiKey/" + print_api_key + "";
            //String FILE_NAME = "opt/files/np_files/test_"+post_id+".pdf";
            System.out.println("2.Load Done: ");

            String FILE_NAME = "C:\\WMS\\test_" + print_post_id + ".pdf";

            try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.out.println("Error UpLoad: ");
                // handle exception
            }
            System.out.println("3.UpLoad Done: ");

            //это список доступных принтеров (для вібиралочки под пользователя)
                /*
                PrintService[] printServiceAll= PrintServiceLookup.lookupPrintServices(null,null);
                for (PrintService printService: printServiceAll
                     ) {
                    System.out.println(printService.getName());
                }
                */
            PDDocument document = PDDocument.load(new File(FILE_NAME));
            System.out.println("4.Printer Found: ");

            //вот сюда надо выбрать принтер в зависимости от зарегистрированного пользователя
            // т.е. к таблице пользователей добавь поле принтер варчар2 а тут

            List<WMSUsersPrintersList> UsersPrintersList =
                    wmsUsersPrintersListRepository.queryUserPrinterDefaultList(print_user_id, print_role_id);

            if (UsersPrintersList.get(0).printer_name != null) {
                PrintService myPrintService = findPrintService(UsersPrintersList.get(0).printer_name);
                System.out.println("5.Printer Name: " + UsersPrintersList.get(0).printer_name);

                if (myPrintService != null) {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(new PDFPageable(document));
                    job.setPrintService(myPrintService);
                    job.print();
                    document.close();
                }
            }

            StoredProcedureQuery PrintQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_PrintPostExchangeSaleDoc")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.OUT)
                    .setParameter(1, print_user_id)
                    .setParameter(2, print_role_id)
                    .setParameter(3, print_post_id)
                    ;
            PrintQuery.execute();

            PrintErrorID = (Integer) PrintQuery.getOutputParameterValue(4);
            PrintErrorValue = (String) PrintQuery.getOutputParameterValue(5);

        } catch (PrinterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @PostMapping("/add_new_post_to_doc")
    public ResponseEntity<?> add_new_post_to_doc(
            @RequestParam(name = "doc_id") Long doc_id,
            @RequestParam(name = "post_id") String post_id,
            @RequestParam(name = "api_key") String api_key
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        System.out.println("doc_id:> "+doc_id);
        System.out.println("post_id:> "+post_id);
        System.out.println("api_key:> "+api_key);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

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

            System.out.println("1.Add Done: ");

            if(ErrorID == 0){
                error_result = new ResponseEntity<>("Посылка загружена", HttpStatus.OK);
                printPost(post_id, api_key, user_List.get(0).id, user_Role_List.get(0).role_id);
            }
            else if(ErrorID == 1){
                error_result = new ResponseEntity<>(ErrorValue, HttpStatus.CONFLICT);
                printPost(post_id, api_key, user_List.get(0).id, user_Role_List.get(0).role_id);
            }
            else {
                System.out.println("1.Error:> ");
                error_result = new ResponseEntity<>(ErrorValue, HttpStatus.CONFLICT);
            }
        }
        catch (Exception e) {
            System.out.println("2.Error:> "+e);
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
            result.setData(menuWMSSaleDocsDetailRepository.queryWMSSaleDocsDetailByID(
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

    @GetMapping("/get_all_not_print_detail")
    public JSONDatatable get_all_not_print_detail(
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

            result.setData(detailAllNotPrintRepository.queryDetailAllNotPrintByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, doc_id
            ));
        }
        return result;
    }

    @GetMapping("/get_post_count")
    public JSONDatatable get_post_count(
            @RequestParam(name="doc_id", defaultValue = "0") Long doc_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(doc_id != null && doc_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(postCountRepository.queryPostCountByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, doc_id
            ));
        }
        return result;
    }
}
