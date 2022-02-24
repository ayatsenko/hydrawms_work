package com.idltd.hydramob.controller.client_requests;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.client_requests.DetailClientRequestFilesList;
import com.idltd.hydramob.entity.client_requests.DetailClientRequestList;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.client_requests.*;
import com.idltd.hydramob.repository.list.Currency_Use_ListRepository;
import com.idltd.hydramob.repository.requests.MenuRequestChatBoxRepository;
import com.idltd.hydramob.repository.requests.RequestFiascoStatusListRepository;
import com.idltd.hydramob.repository.requests.RequestFileRepository;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/client_requests")
public class ClientRequestsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClientRequestsListRepository menuClientRequestsListRepository;
    private MenuClientRequestTimeDetailRepository menuClientRequestTimeDetailRepository;
    private MenuClientRequestFilesListRepository menuClientRequestFilesListRepository;
    private MenuClientRequestChatListRepository menuClientRequestChatListRepository;
    private MenuRequestChatBoxRepository menuRequestChatBoxRepository;
    private DetailClientRequestListRepository detailClientRequestListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private Request_Type_ListRepository request_Type_ListRepository;
    private Load_Type_ListRepository load_Type_ListRepository;
    private Request_Status_ListRepository request_Status_ListRepository;
    private Request_Result_Status_ListRepository request_Result_Status_ListRepository;
    private RequestFiascoStatusListRepository requestFiascoStatusListRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;

    private Mail_ListRepository mail_ListRepository;
    private RequestFileRepository requestFileRepository;
    private DetailClientRequestFilesListRepository detailClientRequestFilesListRepository;

    public ClientRequestsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClientRequestsListRepository menuClientRequestsListRepository,
            MenuClientRequestTimeDetailRepository menuClientRequestTimeDetailRepository,
            MenuClientRequestFilesListRepository menuClientRequestFilesListRepository,
            MenuClientRequestChatListRepository menuClientRequestChatListRepository,
            MenuRequestChatBoxRepository menuRequestChatBoxRepository,
            DetailClientRequestListRepository detailClientRequestListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Request_Type_ListRepository request_Type_ListRepository,
            Load_Type_ListRepository load_Type_ListRepository,
            Request_Status_ListRepository request_Status_ListRepository,
            Request_Result_Status_ListRepository request_Result_Status_ListRepository,
            RequestFiascoStatusListRepository requestFiascoStatusListRepository,
            Contragent_ListRepository contragent_ListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,

            Mail_ListRepository mail_ListRepository,
            RequestFileRepository requestFileRepository,
            DetailClientRequestFilesListRepository detailClientRequestFilesListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuClientRequestsListRepository = menuClientRequestsListRepository;
        this.menuClientRequestTimeDetailRepository = menuClientRequestTimeDetailRepository;
        this.menuClientRequestFilesListRepository = menuClientRequestFilesListRepository;
        this.menuClientRequestChatListRepository = menuClientRequestChatListRepository;
        this.menuRequestChatBoxRepository = menuRequestChatBoxRepository;
        this.detailClientRequestListRepository = detailClientRequestListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.request_Type_ListRepository = request_Type_ListRepository;
        this.load_Type_ListRepository = load_Type_ListRepository;
        this.request_Status_ListRepository = request_Status_ListRepository;
        this.request_Result_Status_ListRepository = request_Result_Status_ListRepository;
        this.requestFiascoStatusListRepository = requestFiascoStatusListRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;

        this.mail_ListRepository = mail_ListRepository;
        this.requestFileRepository = requestFileRepository;
        this.detailClientRequestFilesListRepository = detailClientRequestFilesListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "req_id", required = false) Long req_id,
                              @RequestParam(name = "req_store_id", required = false) Long req_store_id,

                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,
                              @RequestParam(name = "cnt_name", required = false) String cnt_name,
                              @RequestParam(name = "cnt_user_access", required = false) Long cnt_user_access,

                              @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                              @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                              @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
                              @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                              @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,  
                              
                              @RequestParam(name = "client_requests_list_table_order_column", required = false) Long client_requests_list_table_order_column,
                              @RequestParam(name = "client_requests_list_table_order_type", required = false) String client_requests_list_table_order_type,
                              @RequestParam(name = "client_requests_list_table_search", required = false) String client_requests_list_table_search,
                              @RequestParam(name = "client_requests_list_table_pagelen", required = false) Long client_requests_list_table_pagelen,
                              @RequestParam(name = "client_requests_list_table_page", required = false) Long client_requests_list_table_page,

                              @RequestParam(name = "client_requests_filter_id", required = false) Long client_requests_filter_id,
                              @RequestParam(name = "client_requests_filter_start_date", required = false) String client_requests_filter_start_date,
                              @RequestParam(name = "client_requests_filter_end_date", required = false) String client_requests_filter_end_date                              
    ){
        model.addObject("req_id", req_id);
        model.addObject("req_store_id", req_store_id);

        model.addObject("cnt_id", cnt_id);
        model.addObject("cnt_name", cnt_name);
        model.addObject("cnt_user_access", cnt_user_access);
        
        model.addObject("client_requests_list_table_order_column", client_requests_list_table_order_column);
        model.addObject("client_requests_list_table_order_type", client_requests_list_table_order_type);
        model.addObject("client_requests_list_table_search", client_requests_list_table_search);
        model.addObject("client_requests_list_table_pagelen", client_requests_list_table_pagelen);
        model.addObject("client_requests_list_table_page", client_requests_list_table_page);

        model.addObject("clients_list_table_order_column", clients_list_table_order_column);
        model.addObject("clients_list_table_order_type", clients_list_table_order_type);
        model.addObject("clients_list_table_search", clients_list_table_search);
        model.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        model.addObject("clients_list_table_page", clients_list_table_page);

        model.addObject("client_requests_filter_id", client_requests_filter_id);
        model.addObject("client_requests_filter_start_date", client_requests_filter_start_date);
        model.addObject("client_requests_filter_end_date", client_requests_filter_end_date);        
        
        model.setViewName("client_requests/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,

            @RequestParam(name = "client_requests_filter_id", required = false, defaultValue = "0") Long client_requests_filter_id,
            @RequestParam(name = "client_requests_filter_start_date", required = false) String client_requests_filter_start_date,
            @RequestParam(name = "client_requests_filter_end_date", required = false) String client_requests_filter_end_date            
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuClientRequestsListRepository.queryClientRequestsMenuByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, client_requests_filter_id, client_requests_filter_start_date, client_requests_filter_end_date
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
            @RequestParam(value="user_id", required = false, defaultValue = "0") Long user_id,
            @RequestParam(value="edit_type_id", required = false, defaultValue = "0") Long edit_type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;

        if(mode != null && mode == 2 && mode == 4) {
            result.setData(user_ListRepository.queryOPSUserAll());
        }
        else if(mode != null && mode == 1 && edit_type_id == 2) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);

            result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
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
                result.setData(contragent_ListRepository.queryCntByID(cnt_id));
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

            result.setData(detailClientRequestListRepository.queryGetSimpleClientReqDetailByID(
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

        result.setData(menuClientRequestTimeDetailRepository.queryMenuClientRequestTimeDetailByReqID(
                user_List.get(0).id, user_Role_List.get(0).role_id, req_id
        ));
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

            result.setData(menuClientRequestFilesListRepository.queryClientRequestFilesMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, req_id));
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

            result.setData(menuClientRequestChatListRepository.queryClientRequestChatMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, req_id));
        }
        return result;
    }

    @RequestMapping("/list_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="req_id") Long req_id,
                                   @RequestParam(value="req_user_id") Long user_id,
                                   @RequestParam(value="req_status_id") Long req_status_id,

                                   @RequestParam(value = "cnt_id") Long cnt_id,
                                   @RequestParam(value = "cnt_name", required = false) String cnt_name,
                                   @RequestParam(value = "cnt_user_access", required = false) Long cnt_user_access,                                   
                                   
                                   @RequestParam(value="client_requests_list_table_order_column") Long client_requests_list_table_order_column,
                                   @RequestParam(value="client_requests_list_table_order_type") String client_requests_list_table_order_type,
                                   @RequestParam(value="client_requests_list_table_search") String client_requests_list_table_search,
                                   @RequestParam(value="client_requests_list_table_pagelen") Long client_requests_list_table_pagelen,
                                   @RequestParam(value="client_requests_list_table_page") Long client_requests_list_table_page,

                                   @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                   @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                   @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                   @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                   @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                   @RequestParam(name = "client_requests_filter_id", required = false) Long client_requests_filter_id,
                                   @RequestParam(name = "client_requests_filter_start_date", required = false) String client_requests_filter_start_date,
                                   @RequestParam(name = "client_requests_filter_end_date", required = false) String client_requests_filter_end_date
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        
        List<DetailClientRequestList> detailRequestList;
        List<DetailClientRequestList> adminEditList;
        List<DetailClientRequestList> managerEditList;
        List<DetailClientRequestList> managerOpsEditList;
        List<DetailClientRequestList> managerAfterOpsEditList;
        List<DetailClientRequestList> adminAfterOpsEditList;

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Contragent_List> contragent_List;
        List<Service_Type_List> service_Type_List;
        List<Request_Type_List> request_Type_List;
        List<User_List> sale_User_List;
        List<Load_Type_List> load_Type_List;
        List<Request_Status_List> request_Status_List;
        List<User_List> ops_User_List;
        List<Request_Result_Status_List> request_Result_Status_List;

        Long userID;
        Long userRoleID;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("auth_username", authname);

        user_List = user_ListRepository.queryUserByName(authname);

        userID = user_List.get(0).id;
        mav.addObject("auth_user_id", user_List.get(0).id);

        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        userRoleID = user_Role_List.get(0).role_id;
        mav.addObject("auth_role_id", userRoleID);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            service_Type_List = (List<Service_Type_List>) service_Type_ListRepository.findAll();
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", new Long(1));
            request_Type_List = request_Type_ListRepository.queryTypeByID(new Long(1));
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("user_id", user_List.get(0).id);
            sale_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("load_type_id", 0);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_status_id", new Long(2));
            request_Status_List = request_Status_ListRepository.queryNewEditStatusID();
            mav.addObject("req_status_list", request_Status_List);

            mav.addObject("req_profit_predict", 0);
        }
        /*Редактирование простого запроса Админом, GM, SaleDir*/
        else if(mode == 1
                && (user_Role_List.get(0).role_id == 1 || user_Role_List.get(0).role_id == 7 || user_Role_List.get(0).role_id == 8)
                && (req_status_id == 1 || req_status_id == 2)
        ){
            adminEditList = detailClientRequestListRepository.queryGetSimpleClientReqDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            Long newSerID = new Long(adminEditList.get(0).ser_id);
            mav.addObject("ser_id", newSerID);
            service_Type_List = service_Type_ListRepository.queryGetByID(newSerID);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", adminEditList.get(0).req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("req_id", adminEditList.get(0).req_id);

            mav.addObject("user_id", adminEditList.get(0).user_id);
            sale_User_List = user_ListRepository.queryUserByID(adminEditList.get(0).user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_name", adminEditList.get(0).req_name);

            mav.addObject("req_end_date", adminEditList.get(0).req_end_date);

            mav.addObject("req_load_place", adminEditList.get(0).req_load_place);

            mav.addObject("req_tax_place", adminEditList.get(0).req_tax_place);

            mav.addObject("req_clearance_place", adminEditList.get(0).req_clearance_place);

            mav.addObject("req_unload_place", adminEditList.get(0).req_unload_place);

            mav.addObject("req_cargo_note", adminEditList.get(0).req_cargo_note);

            mav.addObject("load_type_id", adminEditList.get(0).load_type_id);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_weight", adminEditList.get(0).req_weight);

            mav.addObject("req_volume", adminEditList.get(0).req_volume);

            mav.addObject("req_dims", adminEditList.get(0).req_dims);

            mav.addObject("req_adr", adminEditList.get(0).req_adr);

            mav.addObject("req_special", adminEditList.get(0).req_special);

            mav.addObject("req_tir_type", adminEditList.get(0).req_tir_type);

            mav.addObject("req_profiles", adminEditList.get(0).req_profiles);

            mav.addObject("req_plan_price", adminEditList.get(0).req_plan_price);

            mav.addObject("req_note", adminEditList.get(0).req_note);

            mav.addObject("req_ops_user_id", adminEditList.get(0).req_ops_user_id);

            if(adminEditList.get(0).req_ops_user_id != null) {
                mav.addObject("req_ops_user_id", adminEditList.get(0).req_ops_user_id);
                ops_User_List = user_ListRepository.queryUserByID(adminEditList.get(0).req_ops_user_id);
                mav.addObject("ops_user_list", ops_User_List);
            }

            mav.addObject("req_status_id", adminEditList.get(0).req_status_id);
            request_Status_List = request_Status_ListRepository.queryAllSimpleEditStatusID();
            mav.addObject("req_status_list", request_Status_List);

            request_Result_Status_List = (List<Request_Result_Status_List>) request_Result_Status_ListRepository.findAll();
            mav.addObject("req_result_status_list", request_Result_Status_List);

            mav.addObject("req_profit_predict", adminEditList.get(0).req_profit_predict);
        }
        /*Редактирование простого запроса Менеджером*/
        else if(mode == 1
                && ((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                && user_List.get(0).id.intValue() == user_id.intValue())
                || user_Role_List.get(0).role_id == 8)
                && (req_status_id == 1 || req_status_id == 2)
        ){
            managerEditList = detailClientRequestListRepository.queryGetSimpleClientReqDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            Long newSerID = new Long(managerEditList.get(0).ser_id);
            mav.addObject("ser_id", newSerID);
            service_Type_List = service_Type_ListRepository.queryGetByID(newSerID);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", managerEditList.get(0).req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("req_id", managerEditList.get(0).req_id);

            mav.addObject("user_id", managerEditList.get(0).user_id);
            sale_User_List = user_ListRepository.queryUserByID(managerEditList.get(0).user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_name", managerEditList.get(0).req_name);

            mav.addObject("req_end_date", managerEditList.get(0).req_end_date);

            mav.addObject("req_load_place", managerEditList.get(0).req_load_place);

            mav.addObject("req_tax_place", managerEditList.get(0).req_tax_place);

            mav.addObject("req_clearance_place", managerEditList.get(0).req_clearance_place);

            mav.addObject("req_unload_place", managerEditList.get(0).req_unload_place);

            mav.addObject("req_cargo_note", managerEditList.get(0).req_cargo_note);

            mav.addObject("load_type_id", managerEditList.get(0).load_type_id);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_weight", managerEditList.get(0).req_weight);

            mav.addObject("req_volume", managerEditList.get(0).req_volume);

            mav.addObject("req_dims", managerEditList.get(0).req_dims);

            mav.addObject("req_adr", managerEditList.get(0).req_adr);

            mav.addObject("req_special", managerEditList.get(0).req_special);

            mav.addObject("req_tir_type", managerEditList.get(0).req_tir_type);

            mav.addObject("req_profiles", managerEditList.get(0).req_profiles);

            mav.addObject("req_plan_price", managerEditList.get(0).req_plan_price);

            mav.addObject("req_note", managerEditList.get(0).req_note);

            mav.addObject("req_ops_user_id", managerEditList.get(0).req_ops_user_id);

            if(managerEditList.get(0).req_ops_user_id != null) {
                mav.addObject("req_ops_user_id", managerEditList.get(0).req_ops_user_id);
                ops_User_List = user_ListRepository.queryUserByID(managerEditList.get(0).req_ops_user_id);
                mav.addObject("ops_user_list", ops_User_List);
            }

            mav.addObject("req_status_id", managerEditList.get(0).req_status_id);
            request_Status_List = request_Status_ListRepository.queryNewEditStatusID();
            mav.addObject("req_status_list", request_Status_List);

            mav.addObject("req_profit_predict", managerEditList.get(0).req_profit_predict);
        }
        /*Редактирование простого запроса OPS*/
        else if(mode == 1
                && (user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                && user_List.get(0).id.intValue() != user_id.intValue()
                && (req_status_id == 2 || req_status_id == 3 || req_status_id == 4)
        ){
            managerOpsEditList  = detailClientRequestListRepository.queryGetSimpleClientReqDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            Long newSerID = new Long(managerOpsEditList.get(0).ser_id);
            mav.addObject("ser_id", newSerID);
            service_Type_List = service_Type_ListRepository.queryGetByID(newSerID);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", managerOpsEditList.get(0).req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("req_id", managerOpsEditList.get(0).req_id);

            mav.addObject("user_id", managerOpsEditList.get(0).user_id);
            sale_User_List = user_ListRepository.queryUserByID(managerOpsEditList.get(0).user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_name", managerOpsEditList.get(0).req_name);

            mav.addObject("req_end_date", managerOpsEditList.get(0).req_end_date);

            mav.addObject("req_load_place", managerOpsEditList.get(0).req_load_place);

            mav.addObject("req_tax_place", managerOpsEditList.get(0).req_tax_place);

            mav.addObject("req_clearance_place", managerOpsEditList.get(0).req_clearance_place);

            mav.addObject("req_unload_place", managerOpsEditList.get(0).req_unload_place);

            mav.addObject("req_cargo_note", managerOpsEditList.get(0).req_cargo_note);

            mav.addObject("load_type_id", managerOpsEditList.get(0).load_type_id);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_weight", managerOpsEditList.get(0).req_weight);

            mav.addObject("req_volume", managerOpsEditList.get(0).req_volume);

            mav.addObject("req_dims", managerOpsEditList.get(0).req_dims);

            mav.addObject("req_adr", managerOpsEditList.get(0).req_adr);

            mav.addObject("req_special", managerOpsEditList.get(0).req_special);

            mav.addObject("req_tir_type", managerOpsEditList.get(0).req_tir_type);

            mav.addObject("req_profiles", managerOpsEditList.get(0).req_profiles);

            mav.addObject("req_plan_price", managerOpsEditList.get(0).req_plan_price);

            mav.addObject("req_note", managerOpsEditList.get(0).req_note);

            mav.addObject("req_ops_id", managerOpsEditList.get(0).req_ops_id);

            mav.addObject("req_ops_user_id", user_List.get(0).id);
            ops_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("ops_user_list", ops_User_List);

            mav.addObject("req_ops_date", managerOpsEditList.get(0).req_ops_date);

            mav.addObject("req_ops_answer", managerOpsEditList.get(0).req_ops_answer);

            mav.addObject("req_ops_note", managerOpsEditList.get(0).req_ops_note);

            if(managerOpsEditList.get(0).req_status_id == 2) {
                mav.addObject("req_status_id", new Long(4));
            }else{
                mav.addObject("req_status_id", managerOpsEditList.get(0).req_status_id);
            }
            request_Status_List = request_Status_ListRepository.queryOpsEditStatusID();
            mav.addObject("req_status_list", request_Status_List);

            mav.addObject("req_profit_predict", managerOpsEditList.get(0).req_profit_predict);
        }
        /*Редактирование простого запроса после OPS*/
        else if(mode == 1
                && (userID.intValue() == user_id.intValue()
                || user_Role_List.get(0).role_id == 8)
                && (req_status_id == 4 || req_status_id == 5 || req_status_id == 6)
        ){
            managerAfterOpsEditList  = detailClientRequestListRepository.queryGetSimpleClientReqDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            Long newSerID = new Long(managerAfterOpsEditList.get(0).ser_id);
            mav.addObject("ser_id", newSerID);
            service_Type_List = service_Type_ListRepository.queryGetByID(newSerID);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", managerAfterOpsEditList.get(0).req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("req_id", managerAfterOpsEditList.get(0).req_id);

            mav.addObject("user_id", managerAfterOpsEditList.get(0).user_id);
            sale_User_List = user_ListRepository.queryUserByID(managerAfterOpsEditList.get(0).user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_name", managerAfterOpsEditList.get(0).req_name);

            mav.addObject("req_end_date", managerAfterOpsEditList.get(0).req_end_date);

            mav.addObject("req_load_place", managerAfterOpsEditList.get(0).req_load_place);

            mav.addObject("req_tax_place", managerAfterOpsEditList.get(0).req_tax_place);

            mav.addObject("req_clearance_place", managerAfterOpsEditList.get(0).req_clearance_place);

            mav.addObject("req_unload_place", managerAfterOpsEditList.get(0).req_unload_place);

            mav.addObject("req_cargo_note", managerAfterOpsEditList.get(0).req_cargo_note);

            mav.addObject("load_type_id", managerAfterOpsEditList.get(0).load_type_id);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_weight", managerAfterOpsEditList.get(0).req_weight);

            mav.addObject("req_volume", managerAfterOpsEditList.get(0).req_volume);

            mav.addObject("req_dims", managerAfterOpsEditList.get(0).req_dims);

            mav.addObject("req_adr", managerAfterOpsEditList.get(0).req_adr);

            mav.addObject("req_special", managerAfterOpsEditList.get(0).req_special);

            mav.addObject("req_tir_type", managerAfterOpsEditList.get(0).req_tir_type);

            mav.addObject("req_profiles", managerAfterOpsEditList.get(0).req_profiles);

            mav.addObject("req_plan_price", managerAfterOpsEditList.get(0).req_plan_price);

            mav.addObject("req_note", managerAfterOpsEditList.get(0).req_note);

            mav.addObject("req_ops_id", managerAfterOpsEditList.get(0).req_ops_id);

            mav.addObject("req_ops_date", managerAfterOpsEditList.get(0).req_ops_date);

            mav.addObject("req_ops_answer", managerAfterOpsEditList.get(0).req_ops_answer);

            mav.addObject("req_ops_note", managerAfterOpsEditList.get(0).req_ops_note);

            mav.addObject("req_ops_user_id", managerAfterOpsEditList.get(0).req_ops_user_id);
            ops_User_List = (List<User_List>) user_ListRepository.findAll();
            mav.addObject("ops_user_list", ops_User_List);

            mav.addObject("req_status_id", new Long(5));
            request_Status_List = request_Status_ListRepository.queryFinishEditStatusID();
            mav.addObject("req_status_list", request_Status_List);

            mav.addObject("req_result_text", managerAfterOpsEditList.get(0).req_result_text);

            mav.addObject("req_result_status_id", managerAfterOpsEditList.get(0).req_result_status_id);
            request_Result_Status_List = (List<Request_Result_Status_List>) request_Result_Status_ListRepository.findAll();
            mav.addObject("req_result_status_list", request_Result_Status_List);

            mav.addObject("req_profit_predict", managerAfterOpsEditList.get(0).req_profit_predict);

            mav.addObject("req_result_note", managerAfterOpsEditList.get(0).req_result_note);
        }
        /*Редактирование простого запроса после OPS или вместо Admin*/
        else if(mode == 1
                && (user_Role_List.get(0).role_id == 1 || user_Role_List.get(0).role_id == 7 || user_Role_List.get(0).role_id == 8)
                && (req_status_id == 3 ||req_status_id == 4 || req_status_id == 5 || req_status_id == 6)
        ){
            adminAfterOpsEditList  = detailClientRequestListRepository.queryGetSimpleClientReqDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            Long newSerID = new Long(adminAfterOpsEditList.get(0).ser_id);
            mav.addObject("ser_id", newSerID);
            service_Type_List = service_Type_ListRepository.queryGetByID(newSerID);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", adminAfterOpsEditList.get(0).req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("req_id", adminAfterOpsEditList.get(0).req_id);

            mav.addObject("user_id", adminAfterOpsEditList.get(0).user_id);
            sale_User_List = user_ListRepository.queryUserByID(adminAfterOpsEditList.get(0).user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_name", adminAfterOpsEditList.get(0).req_name);

            mav.addObject("req_end_date", adminAfterOpsEditList.get(0).req_end_date);

            mav.addObject("req_load_place", adminAfterOpsEditList.get(0).req_load_place);

            mav.addObject("req_tax_place", adminAfterOpsEditList.get(0).req_tax_place);

            mav.addObject("req_clearance_place", adminAfterOpsEditList.get(0).req_clearance_place);

            mav.addObject("req_unload_place", adminAfterOpsEditList.get(0).req_unload_place);

            mav.addObject("req_cargo_note", adminAfterOpsEditList.get(0).req_cargo_note);

            mav.addObject("load_type_id", adminAfterOpsEditList.get(0).load_type_id);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_weight", adminAfterOpsEditList.get(0).req_weight);

            mav.addObject("req_volume", adminAfterOpsEditList.get(0).req_volume);

            mav.addObject("req_dims", adminAfterOpsEditList.get(0).req_dims);

            mav.addObject("req_adr", adminAfterOpsEditList.get(0).req_adr);

            mav.addObject("req_special", adminAfterOpsEditList.get(0).req_special);

            mav.addObject("req_tir_type", adminAfterOpsEditList.get(0).req_tir_type);

            mav.addObject("req_profiles", adminAfterOpsEditList.get(0).req_profiles);

            mav.addObject("req_plan_price", adminAfterOpsEditList.get(0).req_plan_price);

            mav.addObject("req_note", adminAfterOpsEditList.get(0).req_note);

            mav.addObject("req_ops_id", adminAfterOpsEditList.get(0).req_ops_id);

            mav.addObject("req_ops_date", adminAfterOpsEditList.get(0).req_ops_date);

            mav.addObject("req_ops_answer", adminAfterOpsEditList.get(0).req_ops_answer);

            mav.addObject("req_ops_note", adminAfterOpsEditList.get(0).req_ops_note);

            if(adminAfterOpsEditList.get(0).req_ops_user_id != null) {
                mav.addObject("req_ops_user_id", adminAfterOpsEditList.get(0).req_ops_user_id);
                ops_User_List = (List<User_List>) user_ListRepository.findAll();
                mav.addObject("ops_user_list", ops_User_List);
            }

            mav.addObject("req_status_id", new Long(5));
            request_Status_List = request_Status_ListRepository.queryFinishEditStatusID();
            mav.addObject("req_status_list", request_Status_List);

            mav.addObject("req_result_text", adminAfterOpsEditList.get(0).req_result_text);

            mav.addObject("req_result_status_id", adminAfterOpsEditList.get(0).req_result_status_id);
            request_Result_Status_List = (List<Request_Result_Status_List>) request_Result_Status_ListRepository.findAll();
            mav.addObject("req_result_status_list", request_Result_Status_List);

            mav.addObject("req_profit_predict", adminAfterOpsEditList.get(0).req_profit_predict);

            mav.addObject("req_result_note", adminAfterOpsEditList.get(0).req_result_note);
        }
        else if(mode == 2 || mode == 4){
            detailRequestList = detailClientRequestListRepository.queryGetSimpleClientReqDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            Long newSerID = new Long(detailRequestList.get(0).ser_id);
            mav.addObject("ser_id", newSerID);
            service_Type_List = service_Type_ListRepository.queryGetByID(newSerID);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", detailRequestList.get(0).req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("req_id", detailRequestList.get(0).req_id);

            mav.addObject("user_id", detailRequestList.get(0).user_id);
            sale_User_List = user_ListRepository.queryUserByID(detailRequestList.get(0).user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_name", detailRequestList.get(0).req_name);

            mav.addObject("req_end_date", detailRequestList.get(0).req_end_date);

            mav.addObject("req_load_place", detailRequestList.get(0).req_load_place);

            mav.addObject("req_tax_place", detailRequestList.get(0).req_tax_place);

            mav.addObject("req_clearance_place", detailRequestList.get(0).req_clearance_place);

            mav.addObject("req_unload_place", detailRequestList.get(0).req_unload_place);

            mav.addObject("req_cargo_note", detailRequestList.get(0).req_cargo_note);

            mav.addObject("load_type_id", detailRequestList.get(0).load_type_id);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_weight", detailRequestList.get(0).req_weight);

            mav.addObject("req_volume", detailRequestList.get(0).req_volume);

            mav.addObject("req_dims", detailRequestList.get(0).req_dims);

            mav.addObject("req_adr", detailRequestList.get(0).req_adr);

            mav.addObject("req_special", detailRequestList.get(0).req_special);

            mav.addObject("req_tir_type", detailRequestList.get(0).req_tir_type);

            mav.addObject("req_profiles", detailRequestList.get(0).req_profiles);

            mav.addObject("req_plan_price", detailRequestList.get(0).req_plan_price);

            mav.addObject("req_note", detailRequestList.get(0).req_note);

            mav.addObject("req_ops_id", detailRequestList.get(0).req_ops_id);

            mav.addObject("req_ops_date", detailRequestList.get(0).req_ops_date);

            mav.addObject("req_ops_user_id", detailRequestList.get(0).req_ops_user_id);
            if(detailRequestList.get(0).req_ops_user_id != null){
                ops_User_List = user_ListRepository.queryUserByID(detailRequestList.get(0).req_ops_user_id);
                mav.addObject("ops_user_list", ops_User_List);
            }
            mav.addObject("req_ops_answer", detailRequestList.get(0).req_ops_answer);

            mav.addObject("req_ops_note", detailRequestList.get(0).req_ops_note);

            mav.addObject("req_status_id", detailRequestList.get(0).req_status_id);
            request_Status_List = (List<Request_Status_List>) request_Status_ListRepository.findAll();
            mav.addObject("req_status_list", request_Status_List);

            mav.addObject("req_result_text", detailRequestList.get(0).req_result_text);

            mav.addObject("req_result_status_id", detailRequestList.get(0).req_result_status_id);
            request_Result_Status_List = (List<Request_Result_Status_List>) request_Result_Status_ListRepository.findAll();
            mav.addObject("req_result_status_list", request_Result_Status_List);

            mav.addObject("req_profit_predict", detailRequestList.get(0).req_profit_predict);

            mav.addObject("req_result_note", detailRequestList.get(0).req_result_note);
        }
        else {
        }
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);

        mav.addObject("client_requests_list_table_order_column", client_requests_list_table_order_column);
        mav.addObject("client_requests_list_table_order_type", client_requests_list_table_order_type);
        mav.addObject("client_requests_list_table_search", client_requests_list_table_search);
        mav.addObject("client_requests_list_table_pagelen", client_requests_list_table_pagelen);
        mav.addObject("client_requests_list_table_page", client_requests_list_table_page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("client_requests_filter_id", client_requests_filter_id);
        mav.addObject("client_requests_filter_start_date", client_requests_filter_start_date);
        mav.addObject("client_requests_filter_end_date", client_requests_filter_end_date);

        mav.setViewName("/client_requests/list_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmailServiceImpl javaMailSender;

    @PostMapping("/list_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false, defaultValue = "1") Long cnt_id,

            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_type_id", required = false) Long req_type_id,
            @RequestParam(name = "req_name", required = false, defaultValue = "") String req_name,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "user_id", required = false, defaultValue = "9") Long user_id,
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
            @RequestParam(name = "req_plan_price", required = false, defaultValue = "1") String req_plan_price,
            @RequestParam(name = "req_note", required = false) String req_note,
            @RequestParam(name = "req_status_id", required = false) Long req_status_id,

            @RequestParam(name = "req_ops_id", required = false) Long req_ops_id,
            @RequestParam(name = "req_ops_user_id", required = false) Long req_ops_user_id,
            @RequestParam(name = "req_ops_answer", required = false) String req_ops_answer,
            @RequestParam(name = "req_ops_note", required = false) String req_ops_note,

            @RequestParam(name = "req_result_text", required = false) String req_result_text,
            @RequestParam(name = "req_result_note", required = false) String req_result_note,

            @RequestParam(name = "req_result_status_id", required = false, defaultValue = "0") Long req_result_status_id,
            @RequestParam(name = "req_profit_predict", required = false, defaultValue = "0") String req_profit_predict,

            @RequestParam(value="req_type_name", required = false) String req_type_name,
            @RequestParam(value="req_type_sname", required = false) String req_type_sname,
            @RequestParam(value="req_type_description", required = false) String req_type_description,
            @RequestParam(value="req_type_colour", required = false) String req_type_colour,

            @RequestParam(value="client_requests_list_table_order_column", required = false) Long client_requests_list_table_order_column,
            @RequestParam(value="client_requests_list_table_order_type", required = false) String client_requests_list_table_order_type,
            @RequestParam(value="client_requests_list_table_search", required = false) String client_requests_list_table_search,
            @RequestParam(value="client_requests_list_table_pagelen", required = false) Long client_requests_list_table_pagelen,
            @RequestParam(value="client_requests_list_table_page", required = false) Long client_requests_list_table_page,

            @RequestParam(value = "cnt_name", required = false) String cnt_name,
            @RequestParam(value = "cnt_user_access", required = false) Long cnt_user_access,

            @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(name = "client_requests_filter_id", required = false) Long client_requests_filter_id,
            @RequestParam(name = "client_requests_filter_start_date", required = false) String client_requests_filter_start_date,
            @RequestParam(name = "client_requests_filter_end_date", required = false) String client_requests_filter_end_date

    ) throws ParseException {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Mail_List> mail_List;

        List<DetailClientRequestList> detailRequestList;
        List<DetailClientRequestList> managerEditList;
        List<DetailClientRequestList> managerOpsEditList;
        List<DetailClientRequestList> managerAfterOpsEditList;

        List<Contragent_List> contragent_List;
        List<Service_Type_List> service_Type_List;
        List<Request_Type_List> request_Type_List;
        List<User_List> sale_User_List;
        List<Load_Type_List> load_Type_List;
        List<Request_Status_List> request_Status_List;
        List<User_List> ops_User_List;
        List<Request_Result_Status_List> request_Result_Status_List;

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String FormatReqDate = "";
        Date Date2 = null;

        if (req_end_date != "" && req_end_date != null) {
            String ReqDate = req_end_date;
            Date2 = df.parse(ReqDate);
            FormatReqDate = newdf.format(Date2);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        Long userID = user_List.get(0).id;

        int i = 0;

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClientAskQuery = entityManager
                            .createStoredProcedureQuery("PKG_REQUEST.pr_AddAskRequestRole")
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
                            .registerStoredProcedureParameter(26, Long.class, ParameterMode.OUT)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, req_type_id)
                            .setParameter(4, req_name)
                            .setParameter(5, user_id)
                            .setParameter(6, cnt_id)
                            .setParameter(7, ser_id)
                            .setParameter(8, FormatReqDate)
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
                            .setParameter(25, req_profit_predict);
                    AddClientAskQuery.execute();

                    Long ReqID = (Long) AddClientAskQuery.getOutputParameterValue(26);
                    mail_List = mail_ListRepository.queryRequstMail(Long.valueOf(2),ReqID);
                    if(mail_List.size() > 0) {
                        do {
                            javaMailSender.sendSimpleMessage(mail_List.get(i).user_mail, mail_List.get(i).mail_subject, mail_List.get(i).mail_text);
                            i++;
                        }
                        while (i < mail_List.size());
                    }
                break;
                case 1:
                    if (((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                            || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                            && user_List.get(0).id == user_id)
                            || user_Role_List.get(0).role_id == 8)
                            && (req_status_id == 1 || req_status_id == 2)) {

                        StoredProcedureQuery EditClientAskQuery = entityManager
                                .createStoredProcedureQuery("PKG_REQUEST.pr_EditContAskRequestRole")
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
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, req_id)
                                .setParameter(4, req_type_id)
                                .setParameter(5, user_List.get(0).id)
                                .setParameter(6, ser_id)
                                .setParameter(7, FormatReqDate)
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
                                .setParameter(24, req_status_id);
                        EditClientAskQuery.execute();
                    } else if ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                            && user_List.get(0).id != user_id
                            && (req_status_id == 2 || req_status_id == 3 || req_status_id == 4)
                    ) {
                        StoredProcedureQuery EditClientOpsAskQuery = entityManager
                                .createStoredProcedureQuery("PKG_REQUEST.pr_EditOpsContAskRequestRole")
                                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, req_id)
                                .setParameter(4, user_List.get(0).id)
                                .setParameter(5, req_ops_answer)
                                .setParameter(6, req_ops_note)
                                .setParameter(7, req_status_id);
                        EditClientOpsAskQuery.execute();

                    } else if (((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                            || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                            && user_List.get(0).id == user_id)
                            || user_Role_List.get(0).role_id == 8)
                            && (req_status_id == 4 || req_status_id == 5 || req_status_id == 6)) {
                        StoredProcedureQuery EditClientAfterOpsQuery = entityManager
                                .createStoredProcedureQuery("PKG_REQUEST.pr_EditFinalContAskRequestRole")
                                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, req_id)
                                .setParameter(4, req_status_id)
                                .setParameter(5, req_result_text)
                                .setParameter(6, req_result_status_id)
                                .setParameter(7, req_profit_predict)
                                .setParameter(8, user_List.get(0).id)
                                .setParameter(9, req_result_note);
                        EditClientAfterOpsQuery.execute();
                    } else {
                    }
                    mail_List =  mail_ListRepository.queryRequstMail(Long.valueOf(2), req_id);
                    if(mail_List.size() > 0) {
                        do {
                            javaMailSender.sendSimpleMessage(mail_List.get(i).user_mail, mail_List.get(i).mail_subject, mail_List.get(i).mail_text);
                            i++;
                        }
                        while (i < mail_List.size());
                    }
                    break;
                case 2:
                    StoredProcedureQuery DelClientReqAskQuery = entityManager
                            .createStoredProcedureQuery("PKG_REQUEST.pr_DelContAskRequestRole")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, req_id);
                    DelClientReqAskQuery.execute();

                    mail_List = mail_ListRepository.queryRequstMail(Long.valueOf(2),req_id);
                    if(mail_List.size() > 0) {
                        do {
                            javaMailSender.sendSimpleMessage(mail_List.get(i).user_mail, mail_List.get(i).mail_subject, mail_List.get(i).mail_text);
                            i++;
                        }
                        while (i < mail_List.size());
                    }
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

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("ser_id", ser_id);
            service_Type_List = (List<Service_Type_List>) service_Type_ListRepository.findAll();
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", req_type_id);
            request_Type_List = request_Type_ListRepository.queryTypeByID(req_type_id);
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("user_id", user_List.get(0).id);
            sale_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("load_type_id", load_type_id);
            load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
            mav.addObject("load_type_list", load_Type_List);

            mav.addObject("req_status_id", req_status_id);
            request_Status_List = request_Status_ListRepository.queryNewEditStatusID();
            mav.addObject("req_status_list", request_Status_List);
        }
        /*Редактирование простого запроса Менеджером*/
        else if(mode == 1
                && ((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                && user_List.get(0).id.intValue() == user_id.intValue())
                || user_Role_List.get(0).role_id == 8)
                && (req_status_id == 1 || req_status_id == 2)
        ){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(ser_id);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("user_id", user_id);
            sale_User_List = user_ListRepository.queryUserByID(user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_status_id", req_status_id);
            request_Status_List = request_Status_ListRepository.queryNewEditStatusID();
            mav.addObject("req_status_list", request_Status_List);
        }
        /*Редактирование простого запроса OPS*/
        else if(mode == 1
                && (user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                && user_List.get(0).id.intValue() != user_id.intValue()
                && (req_status_id == 2 || req_status_id == 3 || req_status_id == 4)
        ){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(ser_id);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("user_id", user_id);
            sale_User_List = user_ListRepository.queryUserByID(user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_status_id", req_status_id);
            request_Status_List = request_Status_ListRepository.queryOpsEditStatusID();
            mav.addObject("req_status_list", request_Status_List);
        }
        /*Редактирование простого запроса после OPS*/
        else if(mode == 1
                && (userID.intValue() == user_id.intValue()
                || user_Role_List.get(0).role_id == 8)
                && (req_status_id == 4 || req_status_id == 5 || req_status_id == 6)
        ){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(ser_id);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("user_id", user_id);
            sale_User_List = user_ListRepository.queryUserByID(user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_status_id", req_status_id);
            request_Status_List = request_Status_ListRepository.queryFinishEditStatusID();
            mav.addObject("req_status_list", request_Status_List);

            mav.addObject("req_result_status_id", req_result_status_id);
            request_Result_Status_List = (List<Request_Result_Status_List>) request_Result_Status_ListRepository.findAll();
            mav.addObject("req_result_status_list", request_Result_Status_List);
        }
        else if(mode == 2 || mode == 4){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(ser_id);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("user_id", user_id);
            sale_User_List = user_ListRepository.queryUserByID(user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_result_status_id", req_result_status_id);
            request_Result_Status_List = (List<Request_Result_Status_List>) request_Result_Status_ListRepository.findAll();
            mav.addObject("req_result_status_list", request_Result_Status_List);
        }
        else {
        }

        mav.addObject("req_id", req_id);

        mav.addObject("req_name", req_name);

        mav.addObject("req_end_date", req_end_date);

        mav.addObject("req_load_place", req_load_place);

        mav.addObject("req_tax_place", req_tax_place);

        mav.addObject("req_clearance_place", req_clearance_place);

        mav.addObject("req_unload_place", req_unload_place);

        mav.addObject("req_cargo_note", req_cargo_note);

        mav.addObject("load_type_id", load_type_id);
        load_Type_List = (List<Load_Type_List>) load_Type_ListRepository.findAll();
        mav.addObject("load_type_list", load_Type_List);

        mav.addObject("req_weight", req_weight);

        mav.addObject("req_volume", req_volume);

        mav.addObject("req_dims", req_dims);

        mav.addObject("req_adr", req_adr);

        mav.addObject("req_special", req_special);

        mav.addObject("req_tir_type", req_tir_type);

        mav.addObject("req_profiles", req_profiles);

        mav.addObject("req_plan_price", req_plan_price);

        mav.addObject("req_note", req_note);

        mav.addObject("req_ops_id", req_ops_id);

        if(req_ops_user_id != null) {
            mav.addObject("req_ops_user_id", req_ops_user_id);
            ops_User_List = user_ListRepository.queryUserByID(req_ops_user_id);
            mav.addObject("ops_user_list", ops_User_List);
        }
        mav.addObject("req_profit_predict", req_profit_predict);

        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("client_requests_list_table_order_column", client_requests_list_table_order_column);
        mav.addObject("client_requests_list_table_order_type", client_requests_list_table_order_type);
        mav.addObject("client_requests_list_table_search", client_requests_list_table_search);
        mav.addObject("client_requests_list_table_pagelen", client_requests_list_table_pagelen);
        mav.addObject("client_requests_list_table_page", client_requests_list_table_page);

        mav.addObject("client_requests_filter_id", client_requests_filter_id);
        mav.addObject("client_requests_filter_start_date", client_requests_filter_start_date);
        mav.addObject("client_requests_filter_end_date", client_requests_filter_end_date);

        mav.setViewName("/client_requests/list_detail");
        return mav;
    }

    @RequestMapping("/files_download")
    public ResponseEntity<byte[]> download(@RequestParam(name = "req_store_id") Long req_store_id
    ) throws SQLException {
        RequestFile clientRequestFile = (RequestFile) requestFileRepository.queryByReqStoreFileID(req_store_id);

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

    @RequestMapping("/files_detail")
    public ModelAndView files_detail(@RequestParam(value="mode") Long mode,

                                     @RequestParam(value="req_id") Long req_id,
                                     @RequestParam(value="req_name") String req_name,
                                     @RequestParam(value="req_store_id") Long req_store_id,

                                     @RequestParam(value = "cnt_id") Long cnt_id,
                                     @RequestParam(value = "cnt_name", required = false) String cnt_name,
                                     @RequestParam(value = "cnt_user_access", required = false) Long cnt_user_access,

                                     @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                     @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                     @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                     @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                     @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> store_user_List;
        List<User_Role_List> user_Role_List;
        List<DetailClientRequestFilesList> detailRequestFilesList;

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
            detailRequestFilesList = detailClientRequestFilesListRepository.queryClientRequestFileDetailByID(user_List.get(0).id, user_Role_List.get(0).role_id, req_store_id);

            mav.addObject("req_id", detailRequestFilesList.get(0).req_id);

            mav.addObject("req_name",  detailRequestFilesList.get(0).req_name);

            mav.addObject("req_store_id",  detailRequestFilesList.get(0).req_store_id);

            mav.addObject("req_store_date",  detailRequestFilesList.get(0).req_store_date);

            mav.addObject("user_id",  detailRequestFilesList.get(0).user_id);
            store_user_List = user_ListRepository.queryUserByID( detailRequestFilesList.get(0).user_id);
            mav.addObject("store_user_list", store_user_List);

            mav.addObject("req_store_filename",  detailRequestFilesList.get(0).req_store_filename);
        }

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.setViewName("/client_requests/files_detail");
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

            @RequestParam(value="client_requests_files_table_order_column", required = false) Long order_column,
            @RequestParam(value="client_requests_files_table_order_type", required = false) String order_type,
            @RequestParam(value="client_requests_files_table_search", required = false) String table_search,
            @RequestParam(value="client_requests_files_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="client_requests_files_table_page", required = false) Long page,

            @RequestParam(value = "cnt_id") Long cnt_id,
            @RequestParam(value = "cnt_name", required = false) String cnt_name,
            @RequestParam(value = "cnt_user_access", required = false) Long cnt_user_access,

            @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page
    ){
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<DetailClientRequestFilesList> detailRequestFilesList;
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

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("client_requests_files_table_order_column", order_column);
        mav.addObject("client_requests_files_table_order_type", order_type);
        mav.addObject("client_requests_files_table_search", table_search);
        mav.addObject("client_requests_files_table_pagelen", pagelen);
        mav.addObject("client_requests_files_table_page", page);

        mav.setViewName("/client_requests/files_detail");
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
