package com.idltd.hydramob.controller.requests;

import com.idltd.hydramob.entity.Mail_List;
import com.idltd.hydramob.entity.RequestFile;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.requests.DetailRequestFilesList;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.list.Currency_Use_ListRepository;
import com.idltd.hydramob.repository.list.CurrentUserDetailRepository;
import com.idltd.hydramob.repository.requests.*;
import com.idltd.hydramob.utils.JSONDatatable;
import com.idltd.hydramob.utils.mail.EmailServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeUtility;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestsController {

    private User_ListRepository user_ListRepository;
    private CurrentUserDetailRepository currentUserDetailRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuRequestsListRepository menuRequestsListRepository;
    private MenuRequestTimeDetailRepository menuRequestTimeDetailRepository;
    private MenuRequestFilesListRepository menuRequestFilesListRepository;
    private MenuRequestChatListRepository menuRequestChatListRepository;
    private DetailRequestListRepository detailRequestListRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private Request_Type_ListRepository request_Type_ListRepository;
    private Load_Type_ListRepository load_Type_ListRepository;
    private Request_Status_ListRepository request_Status_ListRepository;
    private Request_Result_Status_ListRepository request_Result_Status_ListRepository;
    private RequestFiascoStatusListRepository requestFiascoStatusListRepository;
    private Mail_ListRepository mail_ListRepository;
    private RequestFileRepository requestFileRepository;
    private DetailRequestFilesListRepository detailRequestFilesListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;
    private MenuRequestChatBoxRepository menuRequestChatBoxRepository;

    public RequestsController(
            User_ListRepository user_ListRepository,
            CurrentUserDetailRepository currentUserDetailRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuRequestsListRepository menuRequestsListRepository,
            MenuRequestTimeDetailRepository menuRequestTimeDetailRepository,
            MenuRequestFilesListRepository menuRequestFilesListRepository,
            MenuRequestChatListRepository menuRequestChatListRepository,
            DetailRequestListRepository detailRequestListRepository,
            Contragent_ListRepository contragent_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Request_Type_ListRepository request_Type_ListRepository,
            Load_Type_ListRepository load_Type_ListRepository,
            Request_Status_ListRepository request_Status_ListRepository,
            Request_Result_Status_ListRepository request_Result_Status_ListRepository,
            RequestFiascoStatusListRepository requestFiascoStatusListRepository,
            Mail_ListRepository mail_ListRepository,
            RequestFileRepository requestFileRepository,
            DetailRequestFilesListRepository detailRequestFilesListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,
            MenuRequestChatBoxRepository menuRequestChatBoxRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.currentUserDetailRepository = currentUserDetailRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuRequestsListRepository = menuRequestsListRepository;
        this.menuRequestTimeDetailRepository = menuRequestTimeDetailRepository;
        this.menuRequestFilesListRepository = menuRequestFilesListRepository;
        this.menuRequestChatListRepository = menuRequestChatListRepository;
        this.detailRequestListRepository = detailRequestListRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.request_Type_ListRepository = request_Type_ListRepository;
        this.load_Type_ListRepository = load_Type_ListRepository;
        this.request_Status_ListRepository = request_Status_ListRepository;
        this.request_Result_Status_ListRepository = request_Result_Status_ListRepository;
        this.requestFiascoStatusListRepository = requestFiascoStatusListRepository;
        this.mail_ListRepository = mail_ListRepository;
        this.requestFileRepository = requestFileRepository;
        this.detailRequestFilesListRepository = detailRequestFilesListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;
        this.menuRequestChatBoxRepository = menuRequestChatBoxRepository;
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmailServiceImpl javaMailSender;

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "req_id", required = false) Long req_id,

                              @RequestParam(name = "requests_list_table_order_column", required = false) Long requests_list_table_order_column,
                              @RequestParam(name = "requests_list_table_order_type", required = false) String requests_list_table_order_type,
                              @RequestParam(name = "requests_list_table_search", required = false) String requests_list_table_search,
                              @RequestParam(name = "requests_list_table_pagelen", required = false) Long requests_list_table_pagelen,
                              @RequestParam(name = "requests_list_table_page", required = false) Long requests_list_table_page,

                              @RequestParam(name = "requests_filter_id", required = false, defaultValue = "0") Long requests_filter_id,
                              @RequestParam(name = "requests_filter_start_date", required = false) String requests_filter_start_date,
                              @RequestParam(name = "requests_filter_end_date", required = false) String requests_filter_end_date
    ){
        model.addObject("req_id", req_id);

        model.addObject("requests_list_table_order_column", requests_list_table_order_column);
        model.addObject("requests_list_table_order_type", requests_list_table_order_type);
        model.addObject("requests_list_table_search", requests_list_table_search);
        model.addObject("requests_list_table_pagelen", requests_list_table_pagelen);
        model.addObject("requests_list_table_page", requests_list_table_page);

        model.addObject("requests_filter_id", requests_filter_id);
        model.addObject("requests_filter_start_date", requests_filter_start_date);
        model.addObject("requests_filter_end_date", requests_filter_end_date);

        model.setViewName("requests/cover");
        return model;
    }

    @GetMapping("/open_current_request")
    public ModelAndView index(
           ModelAndView model,
           @RequestParam(name = "req_id", required = false) Long req_id
    ) {
        model.addObject("req_id", req_id);

        model.addObject("requests_list_table_order_column", 0);
        model.addObject("requests_list_table_order_type", "asc");
        model.addObject("requests_list_table_search", req_id);
        model.addObject("requests_list_table_pagelen", 25);
        model.addObject("requests_list_table_page", 1);

        model.setViewName("requests/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "requests_filter_id", required = false, defaultValue = "0") Long requests_filter_id,
            @RequestParam(name = "requests_filter_start_date", required = false) String requests_filter_start_date,
            @RequestParam(name = "requests_filter_end_date", required = false) String requests_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuRequestsListRepository.queryRequestsMenuByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id,requests_filter_id,requests_filter_start_date,requests_filter_end_date
        ));
        return result;
    }

    @GetMapping("/get_request_type")
    public JSONDatatable get_request_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(request_Type_ListRepository.queryTypeByID(1L));
        }
        return result;
    }

    @GetMapping("/get_service_type")
    public JSONDatatable get_service_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;

        if(mode != null && (mode == 0 || mode == 1)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);

            result.setData(service_Type_ListRepository.querySerListByOpsID(user_List.get(0).id));
        }
        else{
            result.setData(service_Type_ListRepository.queryGetAllService());
        }
        return result;
    }

    @GetMapping("/get_users")
    public JSONDatatable get_users(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id", required = false, defaultValue = "0") Long user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;

        if(mode != null && mode == 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);

            result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
        }
        else if(mode != null && mode == 1){
            result.setData(user_ListRepository.queryUserByID(user_id));
        }
        else if(mode != null && mode == 4){
            result.setData(user_ListRepository.queryUserByID(user_id));
        }
        return result;
    }

    @GetMapping("/get_ops_users")
    public JSONDatatable get_ops_users(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="edit_type_id", required = false, defaultValue = "0") Long edit_type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;

        if(mode != null && (mode == 2 || mode == 4)) {
            result.setData(user_ListRepository.queryOPSUserAll());
        }
        else if(mode != null && mode == 1 && edit_type_id == 2) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);

            result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
        }
        else if(mode != null && mode == 1 && edit_type_id == 3) {
            result.setData(user_ListRepository.queryOPSUserAll());
        }
        return result;
    }

    @GetMapping("/get_client_list")
    public JSONDatatable get_client_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id", required = false, defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            if(mode == 0) {
                result.setData(contragent_ListRepository.queryCntByUserID(user_List.get(0).id));
            }
            else{
                result.setData( contragent_ListRepository.queryCntByID(cnt_id));
            }
        }
        return result;
    }

    @GetMapping("/get_load_type")
    public JSONDatatable get_load_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(load_Type_ListRepository.findAll());
        }
        return result;
    }

    @GetMapping("/get_currency")
    public JSONDatatable get_currency(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(currency_Use_ListRepository.queryAllCurrencyListByID());
        }
        return result;
    }

    @GetMapping("/get_request_status")
    public JSONDatatable get_request_status(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="edit_type_id", required = false, defaultValue = "0") Long edit_type_id
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null && mode == 0) {
            result.setData(request_Status_ListRepository.queryNewEditStatusID());
        }
//Редактирование запроса до OPS
        else if(mode != null && mode == 1 && edit_type_id == 1){
            result.setData(request_Status_ListRepository.queryNewEditStatusID());
        }
//Редактирование запроса OPS
        else if(mode != null && mode == 1 && edit_type_id == 2){
            result.setData(request_Status_ListRepository.queryOpsEditStatusID());
        }
//Редактирование запроса после OPS
        else if(mode != null && mode == 1 && edit_type_id == 3){
            result.setData(request_Status_ListRepository.queryFinishEditStatusID());
        }
        else if(mode != null && mode == 4){
            result.setData(request_Status_ListRepository.findAll());
        }
        return result;
    }

    @GetMapping("/get_result_status")
    public JSONDatatable get_result_status(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(request_Result_Status_ListRepository.queryEndResultStatusAll());
        }
        return result;
    }

    @GetMapping("/get_fiasco_status")
    public JSONDatatable get_fiasco_status(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(requestFiascoStatusListRepository.queryFiascoStatusAll());
        }
        return result;
    }

    @GetMapping("/get_request_detail")
    public JSONDatatable get_request_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="req_id") Long req_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailRequestListRepository.queryGetSimpleReqDetailByID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, req_id
            ));
        }
        return result;
    }

    @GetMapping("/get_times_detail")
    public JSONDatatable get_times_detail(
            @RequestParam(value="req_id", defaultValue = "1") Long req_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuRequestTimeDetailRepository.queryMenuRequestTimeDetailByReqID(
                user_List.get(0).id, user_Role_List.get(0).role_id, req_id
        ));
        return result;
    }

    @PostMapping("/get_files_table")
    public JSONDatatable get_files_table(
            @RequestParam(value="req_id") Long req_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuRequestFilesListRepository.queryRequestFilesMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, req_id));
        }
        return result;
    }

    @PostMapping("/get_chat_table")
    public JSONDatatable get_chat_table(
            @RequestParam(value="req_id") Long req_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(req_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuRequestChatListRepository.queryRequestChatMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, req_id));
        }
        return result;
    }

    @GetMapping("/get_service_list")
    public JSONDatatable get_service_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="user_id") Long user_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_Role_List.get(0).role_id == 1 || user_Role_List.get(0).role_id == 7 || user_Role_List.get(0).role_id == 8) {
            result.setData(service_Type_ListRepository.queryGetAllService());
        }
        else{
            result.setData(service_Type_ListRepository.queryGetAllUserService(user_List.get(0).id, user_Role_List.get(0).role_id, user_id, cnt_id));
        }
        return result;
    }
//Add Request
    @PostMapping("/add_request")
    public ModelAndView add_request(
            @RequestParam(name = "req_type_id", required = false) Long req_type_id,
            @RequestParam(name = "req_name", required = false, defaultValue = "") String req_name,
            @RequestParam(name = "user_id") Long user_id,
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "req_end_date", required = false) String req_end_date,
            @RequestParam(name = "req_load_place", required = false) String req_load_place,
            @RequestParam(name = "req_tax_place", required = false) String req_tax_place,
            @RequestParam(name = "req_clearance_place", required = false) String req_clearance_place,
            @RequestParam(name = "req_unload_place", required = false) String req_unload_place,
            @RequestParam(name = "req_cargo_note", required = false) String req_cargo_note,
            @RequestParam(name = "load_type_id", required = false, defaultValue = "1") Long load_type_id,
            @RequestParam(name = "req_weight", required = false) String req_weight,
            @RequestParam(name = "req_volume", required = false) String req_volume,
            @RequestParam(name = "req_dims", required = false) String req_dims,
            @RequestParam(name = "req_adr", required = false) String req_adr,
            @RequestParam(name = "req_special", required = false) String req_special,
            @RequestParam(name = "req_tir_type", required = false) String req_tir_type,
            @RequestParam(name = "req_profiles", required = false) String req_profiles,
            @RequestParam(name = "req_plan_price", required = false, defaultValue = "0") String req_plan_price,
            @RequestParam(name = "req_note", required = false) String req_note,
            @RequestParam(name = "req_status_id", required = false) Long req_status_id,
            @RequestParam(name = "req_profit_predict", required = false, defaultValue = "0") String req_profit_predict,
            @RequestParam(name = "req_load_deep", required = false) String req_load_deep,
            @RequestParam(name = "req_load_high", required = false) String req_load_high,
            @RequestParam(name = "req_load_width", required = false) String req_load_width,
            @RequestParam(name = "req_plan_currency_id", required = false, defaultValue = "0") Long req_plan_currency_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddAskQuery = entityManager
                    .createStoredProcedureQuery("PKG_REQUEST.pr_AddAskRequestRoleNew")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(29, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(30, Long.class, ParameterMode.OUT)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, req_type_id)
                    .setParameter(4, req_name)
                    .setParameter(5, user_id)
                    .setParameter(6, cnt_id)
                    .setParameter(7, ser_id)
                    .setParameter(8, req_end_date)
                    .setParameter(9, req_load_place)
                    .setParameter(10, req_tax_place)
                    .setParameter(11, req_clearance_place)
                    .setParameter(12, req_unload_place)
                    .setParameter(13, req_cargo_note)
                    .setParameter(14, load_type_id)
                    .setParameter(15, req_weight)
                    .setParameter(16, req_volume)
                    .setParameter(17, req_dims)
                    .setParameter(18, req_adr)
                    .setParameter(19, req_special)
                    .setParameter(20, req_tir_type)
                    .setParameter(21, req_profiles)
                    .setParameter(22, req_plan_price)
                    .setParameter(23, req_note)
                    .setParameter(24, req_status_id)
                    .setParameter(25, req_profit_predict)
                    .setParameter(26, req_load_deep)
                    .setParameter(27, req_load_high)
                    .setParameter(28, req_load_width)
                    .setParameter(29, req_plan_currency_id)
                    ;
            AddAskQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

//Edit Before OPS
    @PostMapping("/edit_request_before_ops")
    public ModelAndView edit_request_before_ops(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_type_id", required = false) Long req_type_id,
            @RequestParam(name = "user_id") Long user_id,
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "req_end_date", required = false) String req_end_date,
            @RequestParam(name = "req_load_place", required = false) String req_load_place,
            @RequestParam(name = "req_tax_place", required = false) String req_tax_place,
            @RequestParam(name = "req_clearance_place", required = false) String req_clearance_place,
            @RequestParam(name = "req_unload_place", required = false) String req_unload_place,
            @RequestParam(name = "req_cargo_note", required = false) String req_cargo_note,
            @RequestParam(name = "load_type_id", required = false, defaultValue = "1") Long load_type_id,
            @RequestParam(name = "req_weight", required = false) String req_weight,
            @RequestParam(name = "req_volume", required = false) String req_volume,
            @RequestParam(name = "req_dims", required = false) String req_dims,
            @RequestParam(name = "req_adr", required = false) String req_adr,
            @RequestParam(name = "req_special", required = false) String req_special,
            @RequestParam(name = "req_tir_type", required = false) String req_tir_type,
            @RequestParam(name = "req_profiles", required = false) String req_profiles,
            @RequestParam(name = "req_plan_price", required = false, defaultValue = "0") String req_plan_price,
            @RequestParam(name = "req_note", required = false) String req_note,
            @RequestParam(name = "req_status_id", required = false) Long req_status_id,
            @RequestParam(name = "req_profit_predict", required = false, defaultValue = "0") String req_profit_predict,
            @RequestParam(name = "req_load_deep", required = false) String req_load_deep,
            @RequestParam(name = "req_load_high", required = false) String req_load_high,
            @RequestParam(name = "req_load_width", required = false) String req_load_width,
            @RequestParam(name = "req_plan_currency_id", required = false, defaultValue = "0") Long req_plan_currency_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery ManagerEditAskQuery = entityManager
                    .createStoredProcedureQuery("PKG_REQUEST.pr_EditContAskRequestRoleNew")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, req_id)
                    .setParameter(4, req_type_id)
                    .setParameter(5, user_id)
                    .setParameter(6, ser_id)
                    .setParameter(7, req_end_date)
                    .setParameter(8, req_load_place)
                    .setParameter(9, req_tax_place)
                    .setParameter(10, req_clearance_place)
                    .setParameter(11, req_unload_place)
                    .setParameter(12, req_cargo_note)
                    .setParameter(13, load_type_id)
                    .setParameter(14, req_weight)
                    .setParameter(15, req_volume)
                    .setParameter(16, req_dims)
                    .setParameter(17, req_adr)
                    .setParameter(18, req_special)
                    .setParameter(19, req_tir_type)
                    .setParameter(20, req_profiles)
                    .setParameter(21, req_plan_price)
                    .setParameter(22, req_note)
                    .setParameter(23, req_profit_predict)
                    .setParameter(24, req_status_id)
                    .setParameter(25, req_load_deep)
                    .setParameter(26, req_load_high)
                    .setParameter(27, req_load_width)
                    .setParameter(28, req_plan_currency_id)
                    ;
            ManagerEditAskQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

//Edit OPS
    @PostMapping("/edit_request_ops")
    public ModelAndView edit_request_ops(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_ops_user_id", required = false) Long req_ops_user_id,
            @RequestParam(name = "req_ops_answer", required = false) String req_ops_answer,
            @RequestParam(name = "req_ops_note", required = false) String req_ops_note,
            @RequestParam(name = "req_ops_currency_id", required = false, defaultValue = "0") Long req_ops_currency_id,
            @RequestParam(name = "req_ops_price", required = false) String req_ops_price,
            @RequestParam(name = "req_status_id", required = false) Long req_status_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditOpsAskQuery = entityManager
                    .createStoredProcedureQuery("PKG_REQUEST.pr_EditOpsContAskRequestRoleNew")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, req_id)
                    .setParameter(4, req_ops_user_id)
                    .setParameter(5, req_ops_answer)
                    .setParameter(6, req_ops_note)
                    .setParameter(7, req_status_id)
                    .setParameter(8, req_ops_currency_id)
                    .setParameter(9, req_ops_price)
                    ;
            EditOpsAskQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

//Edit After OPS
    @PostMapping("/edit_request_after_ops")
    public ModelAndView edit_request_after_ops(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_status_id", required = false) Long req_status_id,
            @RequestParam(name = "req_result_text", required = false) String req_result_text,
            @RequestParam(name = "req_result_note", required = false) String req_result_note,
            @RequestParam(name = "req_result_status_id", required = false, defaultValue = "0") Long req_result_status_id,
            @RequestParam(name = "req_profit_predict", required = false, defaultValue = "0") String req_profit_predict,
            @RequestParam(name = "req_result_currency_id", required = false, defaultValue = "0") Long req_result_currency_id,
            @RequestParam(name = "req_result_price", required = false, defaultValue = "0") String req_result_price,
            @RequestParam(name = "req_fia_status_id", required = false, defaultValue = "5") Long req_fia_status_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_REQUEST.pr_EditFinalContAskRequestRoleNew")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, req_id)
                    .setParameter(4, req_status_id)
                    .setParameter(5, req_result_text)
                    .setParameter(6, req_result_status_id)
                    .setParameter(7, req_profit_predict)
                    .setParameter(8, user_List.get(0).id)
                    .setParameter(9, req_result_note)
                    .setParameter(10, req_result_currency_id)
                    .setParameter(11, req_result_price)
                    .setParameter(12, req_fia_status_id)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

//Edit Win
    @PostMapping("/edit_request_win")
    public ModelAndView edit_request_win(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_status_id", required = false) Long req_status_id,
            @RequestParam(name = "req_result_text", required = false) String req_result_text,
            @RequestParam(name = "req_result_note", required = false) String req_result_note,
            @RequestParam(name = "req_result_status_id", required = false, defaultValue = "0") Long req_result_status_id,
            @RequestParam(name = "req_profit_predict", required = false, defaultValue = "0") String req_profit_predict,
            @RequestParam(name = "req_result_currency_id", required = false, defaultValue = "0") Long req_result_currency_id,
            @RequestParam(name = "req_result_price", required = false, defaultValue = "0") String req_result_price,

            @RequestParam(name = "req_doc_number", required = false) String req_doc_number,
            @RequestParam(name = "req_doc_start_date", required = false) String req_doc_start_date,
            @RequestParam(name = "req_doc_end_date", required = false) String req_doc_end_date,
            @RequestParam(name = "req_work_start_date", required = false) String req_work_start_date,
            @RequestParam(name = "req_work_note", required = false) String req_work_note
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_REQUEST.pr_EditAfterAllContAskRequestRoleNew")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, req_id)
                    .setParameter(4, req_status_id)
                    .setParameter(5, req_result_text)
                    .setParameter(6, req_result_status_id)
                    .setParameter(7, req_profit_predict)
                    .setParameter(8, user_List.get(0).id)
                    .setParameter(9, req_result_note)
                    .setParameter(10, req_work_note)
                    .setParameter(11, req_doc_number)
                    .setParameter(12, req_doc_start_date)
                    .setParameter(13, req_doc_end_date)
                    .setParameter(14, req_work_start_date)
                    .setParameter(15, req_result_currency_id)
                    .setParameter(16, req_result_price)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

//Delete Request
    @PostMapping("/del_request")
    public ModelAndView del_request(
            @RequestParam(name = "req_id", required = false) Long req_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_REQUEST.pr_DelContAskRequestRole")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, req_id)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/requests_clone")
    public ModelAndView requests_clone(
            @RequestParam(name = "req_id") Long req_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery SimpReqCloneQuery = entityManager
                .createStoredProcedureQuery("PKG_REQUEST.pr_CloneAskRequestRole")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, req_id);
        SimpReqCloneQuery.execute();
        return null;
    }

    @RequestMapping("/files_download")
    public ResponseEntity<byte[]> download(@RequestParam(name = "req_store_id") Long req_store_id
    ) throws SQLException {
        RequestFile clientRequestFile = requestFileRepository.queryByReqStoreCurrFileID(req_store_id);

        int blobLength = (int) clientRequestFile.req_store_file_body.length();
        byte[] output = clientRequestFile.req_store_file_body.getBytes(1, blobLength);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(clientRequestFile.req_store_file_contenttype));
        responseHeaders.setContentLength(output.length);
        try {
            responseHeaders.set("Content-disposition", "attachment; filename="+ MimeUtility.encodeWord(clientRequestFile.req_store_filename, "utf-8","Q")
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }

    //Delete Request
    @PostMapping("/add_request_file")
    public ModelAndView add_request_file(
            @RequestParam(name = "req_id") Long req_id,
            @RequestParam("file") MultipartFile file
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        RequestFile request_file = new RequestFile();

        request_file.req_id = req_id;
        request_file.user_id = user_List.get(0).id;
        request_file.req_store_filename = file.getOriginalFilename();
        request_file.req_store_file_contenttype = file.getContentType();

        try (InputStream inputStream = file.getInputStream()) {
            byte[] obj_in = IOUtils.toByteArray(inputStream);
            request_file.req_store_file_body = new SerialBlob(obj_in);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request_file = requestFileRepository.save(request_file);
        return null;
    }

    //Delete Request
    @PostMapping("/del_request_file")
    public ModelAndView del_request_file(
            @RequestParam(name = "req_store_id") Long req_store_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelRequestFileQuery = entityManager
                    .createStoredProcedureQuery("PKG_ADMIN.pr_DelReqFile")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .setParameter(1, req_store_id);
            DelRequestFileQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/files_detail")
    public ModelAndView files_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="req_id") Long req_id,
                                     @RequestParam(value="req_name") String req_name,
                                     @RequestParam(value="req_store_id") Long req_store_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> store_user_List;
        List<User_Role_List> user_Role_List;
        List<DetailRequestFilesList> detailRequestFilesList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("req_id", req_id);

            mav.addObject("req_name", req_name);

            mav.addObject("user_id", user_List.get(0).id);
            store_user_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("store_user_list", store_user_List);
        }
        else{
            detailRequestFilesList = detailRequestFilesListRepository.queryRequestFileDetailByID(user_List.get(0).id, user_Role_List.get(0).role_id, req_store_id);

            mav.addObject("req_id", detailRequestFilesList.get(0).req_id);

            mav.addObject("req_name",  detailRequestFilesList.get(0).req_name);

            mav.addObject("req_store_id",  detailRequestFilesList.get(0).req_store_id);

            mav.addObject("req_store_date",  detailRequestFilesList.get(0).req_store_date);

            mav.addObject("user_id",  detailRequestFilesList.get(0).user_id);
            store_user_List = user_ListRepository.queryUserByID( detailRequestFilesList.get(0).user_id);
            mav.addObject("store_user_list", store_user_List);

            mav.addObject("req_store_filename",  detailRequestFilesList.get(0).req_store_filename);
        }

        mav.setViewName("/requests/files_detail");
        return mav;
    }

    @PostMapping("/files_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="req_id") Long req_id,
            @RequestParam(value="req_name", required = false) String req_name,
            @RequestParam(value="req_store_id", required = false) Long req_store_id,
            @RequestParam(value="req_store_date", required = false) String req_store_date,
            @RequestParam("file") MultipartFile file,

            @RequestParam(value="requests_files_table_order_column", required = false) Long order_column,
            @RequestParam(value="requests_files_table_order_type", required = false) String order_type,
            @RequestParam(value="requests_files_table_search", required = false) String table_search,
            @RequestParam(value="requests_files_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="requests_files_table_page", required = false) Long page
    ){
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<DetailRequestFilesList> detailRequestFilesList;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);

        try {
            RequestFile request_file = new RequestFile();
            switch (mode.intValue()) {
                case 0:
                    request_file.req_id = req_id;
                    request_file.req_store_id = req_store_id;
                    request_file.user_id = user_List.get(0).id;
                    request_file.req_store_filename = file.getOriginalFilename();
                    request_file.req_store_file_contenttype = file.getContentType();

                    try (InputStream inputStream = file.getInputStream()) {
                        byte[] obj_in = IOUtils.toByteArray(inputStream);
                        request_file.req_store_file_body = new SerialBlob(obj_in);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SerialException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request_file = requestFileRepository.save(request_file);
                    break;
                case 2:
                    StoredProcedureQuery DelRequestFileQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_DelReqFile")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .setParameter(1, req_store_id);
                    DelRequestFileQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("req_id", req_id);
        mav.addObject("req_name", req_name);
        mav.addObject("req_store_id", req_store_id);

        mav.addObject("requests_files_table_order_column", order_column);
        mav.addObject("requests_files_table_order_type", order_type);
        mav.addObject("requests_files_table_search", table_search);
        mav.addObject("requests_files_table_pagelen", pagelen);
        mav.addObject("requests_files_table_page", page);

        mav.setViewName("/requests/files_detail");
        return mav;
    }

    @RequestMapping("/chat_add")
    public ModelAndView editor(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_chat_text", required = false) String req_chat_text
    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Mail_List> mail_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddRequestChatQuery = entityManager
                .createStoredProcedureQuery("PKG_REQUEST.pr_AddReqChat")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT)
                .setParameter(1, req_id)
                .setParameter(2, user_List.get(0).id)
                .setParameter(3, user_Role_List.get(0).role_id)
                .setParameter(4, req_chat_text);
        AddRequestChatQuery.execute();
        Long ReqChatID = (Long) AddRequestChatQuery.getOutputParameterValue(5);

        mail_List = mail_ListRepository.queryRequstChatMail(Long.valueOf(1),ReqChatID);
        if(mail_List.get(0).user_id > 0) {
            int i = 0;
            do {
                javaMailSender.sendSimpleMessage(mail_List.get(i).user_mail, mail_List.get(i).mail_subject, mail_List.get(i).mail_text);
                i++;
            }
            while (i < mail_List.size());
        }
        return null;
    }

    @GetMapping("/get_sale_list")
    public JSONDatatable get_sale_list(
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(user_ListRepository.querySaleUserAll());
        return result;
    }

    @PostMapping("/list_link_edit")
    public ModelAndView list_link_edit(
            @RequestParam(name = "req_id") Long req_id,
            @RequestParam(name = "first_user_id") Long first_user_id,
            @RequestParam(name = "second_user_id") Long second_user_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(first_user_id != null && second_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelClientCNTQuery = entityManager
                    .createStoredProcedureQuery("PKG_REQUEST.pr_editSaleUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, req_id)
                    .setParameter(4, first_user_id)
                    .setParameter(5, second_user_id)
                    ;
            DelClientCNTQuery.execute();
        }
        return null;
    }

    //Chat
    @GetMapping("/get_chat_box")
    public JSONDatatable get_chat_box(
            @RequestParam(name = "req_id", defaultValue = "0") Long req_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if (req_id != null && req_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuRequestChatBoxRepository.queryRequestChatBoxByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_id
            ));
        }
        return result;
    }
}
