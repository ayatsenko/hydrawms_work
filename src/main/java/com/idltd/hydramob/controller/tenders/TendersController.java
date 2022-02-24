package com.idltd.hydramob.controller.tenders;

import com.idltd.hydramob.entity.Mail_List;
import com.idltd.hydramob.entity.TenderFile;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.tenders.*;
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

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tenders")
public class TendersController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuTendersListRepository menuTendersListRepository;
    private DetailTenderListRepository detailTenderListRepository;
    private Request_Result_Status_ListRepository tender_Result_Status_ListRepository;
    private TenderFiascoStatusListRepository tenderFiascoStatusListRepository;

    private MenuTenderChatListRepository menuTenderChatListRepository;
    private TenderFileRepository tenderFileRepository;

    private DetailTenderFilesListRepository detailTenderFilesListRepository;
    private Mail_ListRepository mail_ListRepository;

    private MenuTenderFilesListRepository menuTenderFilesListRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private Request_Type_ListRepository tender_Type_ListRepository;
    private Tender_Status_ListRepository tender_Status_ListRepository;
    private Request_Tend_Status_ListRepository tender_Tend_Status_ListRepository;

    private MenuTenderChatBoxRepository menuTenderChatBoxRepository;
    public TendersController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuTendersListRepository menuTendersListRepository,
            DetailTenderListRepository detailTenderListRepository,
            Request_Result_Status_ListRepository tender_Result_Status_ListRepository,
            TenderFiascoStatusListRepository tenderFiascoStatusListRepository,

            MenuTenderChatListRepository menuTenderChatListRepository,
            DetailTenderFilesListRepository detailTenderFilesListRepository,
            Mail_ListRepository mail_ListRepository,

            MenuTenderFilesListRepository menuTenderFilesListRepository,
            TenderFileRepository tenderFileRepository,
            Contragent_ListRepository contragent_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Request_Type_ListRepository tender_Type_ListRepository,
            Tender_Status_ListRepository tender_Status_ListRepository,
            Request_Tend_Status_ListRepository tender_Tend_Status_ListRepository,

            MenuTenderChatBoxRepository menuTenderChatBoxRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuTendersListRepository = menuTendersListRepository;
        this.detailTenderListRepository = detailTenderListRepository;
        this.tender_Result_Status_ListRepository = tender_Result_Status_ListRepository;
        this.tenderFiascoStatusListRepository = tenderFiascoStatusListRepository;

        this.menuTenderChatListRepository = menuTenderChatListRepository;
        this.detailTenderFilesListRepository = detailTenderFilesListRepository;
        this.mail_ListRepository = mail_ListRepository;

        this.menuTenderFilesListRepository = menuTenderFilesListRepository;
        this.tenderFileRepository = tenderFileRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.tender_Type_ListRepository = tender_Type_ListRepository;
        this.tender_Status_ListRepository = tender_Status_ListRepository;
        this.tender_Tend_Status_ListRepository = tender_Tend_Status_ListRepository;

        this.menuTenderChatBoxRepository = menuTenderChatBoxRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "req_id", required = false) Long req_id,

                              @RequestParam(name = "tenders_list_table_order_column", required = false) Long tenders_list_table_order_column,
                              @RequestParam(name = "tenders_list_table_order_type", required = false) String tenders_list_table_order_type,
                              @RequestParam(name = "tenders_list_table_search", required = false) String tenders_list_table_search,
                              @RequestParam(name = "tenders_list_table_pagelen", required = false) Long tenders_list_table_pagelen,
                              @RequestParam(name = "tenders_list_table_page", required = false) Long tenders_list_table_page,

                              @RequestParam(name = "tender_filter_id", required = false, defaultValue = "0") Long tender_filter_id,
                              @RequestParam(name = "tender_filter_start_date", required = false) String tender_filter_start_date,
                              @RequestParam(name = "tender_filter_end_date", required = false) String tender_filter_end_date,

                              @RequestParam(name = "tenders_files_table_order_column", required = false) Long tenders_files_table_order_column,
                              @RequestParam(name = "tenders_files_table_order_type", required = false) String tenders_files_table_order_type,
                              @RequestParam(name = "tenders_files_table_search", required = false) String tenders_files_table_search,
                              @RequestParam(name = "tenders_files_table_pagelen", required = false) Long tenders_files_table_pagelen,
                              @RequestParam(name = "tenders_files_table_page", required = false) Long tenders_files_table_page
    ){
        model.addObject("req_id", req_id);

        model.addObject("tenders_list_table_order_column", tenders_list_table_order_column);
        model.addObject("tenders_list_table_order_type", tenders_list_table_order_type);
        model.addObject("tenders_list_table_search", tenders_list_table_search);
        model.addObject("tenders_list_table_pagelen", tenders_list_table_pagelen);
        model.addObject("tenders_list_table_page", tenders_list_table_page);

        model.addObject("tender_filter_id", tender_filter_id);
        model.addObject("tender_filter_start_date", tender_filter_start_date);
        model.addObject("tender_filter_end_date", tender_filter_end_date);

        model.addObject("tenders_files_table_order_column", tenders_files_table_order_column);
        model.addObject("tenders_files_table_order_type", tenders_files_table_order_type);
        model.addObject("tenders_files_table_search", tenders_files_table_search);
        model.addObject("tenders_files_table_pagelen", tenders_files_table_pagelen);
        model.addObject("tenders_files_table_page", tenders_files_table_page);

        model.setViewName("tenders/cover");
        return model;
    }

    @GetMapping("/open_current_tender")
    public ModelAndView index(
            ModelAndView model,
            @RequestParam(name = "req_id", required = false) Long req_id
    ) {
        model.addObject("req_id", req_id);

        model.addObject("tenders_list_table_order_column", 0);
        model.addObject("tenders_list_table_order_type", "asc");
        model.addObject("tenders_list_table_search", req_id);
        model.addObject("tenders_list_table_pagelen", 25);
        model.addObject("tenders_list_table_page", 1);

        model.setViewName("tenders/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "tender_filter_id", required = false, defaultValue = "0") Long tender_filter_id,
            @RequestParam(name = "tender_filter_start_date", required = false) String tender_filter_start_date,
            @RequestParam(name = "tender_filter_end_date", required = false) String tender_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuTendersListRepository.queryTendersMenuListByUserID(
                user_List.get(0).id,user_Role_List.get(0).role_id, tender_filter_id, tender_filter_start_date, tender_filter_end_date
        ));

        return result;
    }

    @GetMapping("/get_tender_type")
    public JSONDatatable get_tender_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(tender_Type_ListRepository.queryTypeByID(2L));
        }
        return result;
    }

    @GetMapping("/get_service_type")
    public JSONDatatable get_service_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null && (mode == 0 || mode == 1)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(service_Type_ListRepository.queryGetUserService(user_List.get(0).id,user_Role_List.get(0).role_id));
        }
        else{
            result.setData(service_Type_ListRepository.queryGetAllService());
        }
        return result;
    }

    @GetMapping("/get_status_list")
    public JSONDatatable get_status_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="edit_type_id", required = false) Long edit_type_id
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null && mode == 0) {
            result.setData(tender_Status_ListRepository.queryNewEditStatusID());
        }
        else if(mode != null && mode == 1 && edit_type_id == 1){
            result.setData(tender_Status_ListRepository.queryNewEditStatusID());
        }
        else if(mode != null && mode == 1 && edit_type_id == 2){
            result.setData(tender_Status_ListRepository.queryOpsEditStatusID());
        }
        else if(mode != null && mode == 1 && edit_type_id == 3){
            result.setData(tender_Status_ListRepository.queryFinishEditStatusID());
        }
        else if(mode != null && (mode == 2 || mode == 4)) {
            result.setData(tender_Status_ListRepository.findAll());
        }
        return result;
    }

    @GetMapping("/get_result_status_list")
    public JSONDatatable get_result_status_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(tender_Tend_Status_ListRepository.queryFinalTendStatusID());
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
        else if(mode != null && mode == 1 && (edit_type_id == 1 || edit_type_id == 3)) {
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

    @GetMapping("/get_fiasco_status")
    public JSONDatatable get_fiasco_status(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(tenderFiascoStatusListRepository.queryFiascoStatusAll());
        }
        return result;
    }

    @GetMapping("/get_tender_detail")
    public JSONDatatable get_tender_detail(
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

            result.setData(detailTenderListRepository.queryTenderDetailByID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, req_id
            ));
        }
        return result;
    }

    @PostMapping("/add_tender")
    public ModelAndView add_tender(
            @RequestParam(name = "cnt_id", required = false, defaultValue = "1") Long cnt_id,
            @RequestParam(name = "req_type_id", required = false, defaultValue = "2") Long req_type_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "req_note", required = false) String req_note,
            @RequestParam(name = "req_start_date", required = false) String req_tend_date,
            @RequestParam(name = "req_end_date", required = false) String req_tend_end_date,
            @RequestParam(name = "req_tend_runway", required = false) String req_tend_runway,
            @RequestParam(name = "req_tend_autotype", required = false) String req_tend_autotype,
            @RequestParam(name = "req_tend_count", required = false) String req_tend_count,
            @RequestParam(name = "tend_status_id", required = false) Long tend_status_id,
            @RequestParam(name = "req_profit_predict", required = false) String req_profit_predict
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddTendQuery = entityManager
                .createStoredProcedureQuery("PKG_TENDERS.pr_AddTendContRequest")
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
                .registerStoredProcedureParameter(15, Long.class, ParameterMode.OUT)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, req_type_id)
                .setParameter(4, user_id)
                .setParameter(5, cnt_id)
                .setParameter(6, ser_id)
                .setParameter(7, req_note)
                .setParameter(8, req_tend_date)
                .setParameter(9, req_tend_end_date)
                .setParameter(10, req_tend_runway)
                .setParameter(11, req_tend_autotype)
                .setParameter(12, req_tend_count)
                .setParameter(13, tend_status_id)
                .setParameter(14, req_profit_predict);
        AddTendQuery.execute();
        return null;
    }

    @PostMapping("/edit_user_tender")
    public ModelAndView edit_user_tender(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "cnt_id", required = false, defaultValue = "1") Long cnt_id,
            @RequestParam(name = "req_type_id", required = false, defaultValue = "2") Long req_type_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "req_note", required = false) String req_note,
            @RequestParam(name = "req_start_date", required = false) String req_tend_date,
            @RequestParam(name = "req_end_date", required = false) String req_tend_end_date,
            @RequestParam(name = "req_tend_runway", required = false) String req_tend_runway,
            @RequestParam(name = "req_tend_autotype", required = false) String req_tend_autotype,
            @RequestParam(name = "req_tend_count", required = false) String req_tend_count,
            @RequestParam(name = "tend_status_id", required = false) Long tend_status_id,
            @RequestParam(name = "req_profit_predict", required = false) String req_profit_predict,
            @RequestParam(name = "req_result_status_id", required = false, defaultValue = "0") Long req_result_status_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditTendQuery = entityManager
                .createStoredProcedureQuery("PKG_TENDERS.pr_EditContTendRequest")
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
                .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, req_id)
                .setParameter(4, req_type_id)
                .setParameter(5, user_id)
                .setParameter(6, ser_id)
                .setParameter(7, req_note)
                .setParameter(8, req_tend_date)
                .setParameter(9, req_tend_end_date)
                .setParameter(10, req_tend_runway)
                .setParameter(11, req_tend_autotype)
                .setParameter(12, req_tend_count)
                .setParameter(13, tend_status_id)
                .setParameter(14, req_result_status_id)
                .setParameter(15, req_profit_predict);
        EditTendQuery.execute();
        return null;
    }

    @PostMapping("/edit_ops_tender")
    public ModelAndView edit_ops_tender(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_ops_answer", required = false) String req_ops_answer,
            @RequestParam(name = "req_ops_note", required = false) String req_ops_note,
            @RequestParam(name = "tend_status_id", required = false) Long tend_status_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditOpsTendQuery = entityManager
                .createStoredProcedureQuery("PKG_TENDERS.pr_EditOpsContTendRequest")
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
                .setParameter(7, tend_status_id);
        EditOpsTendQuery.execute();
        return null;
    }

    @PostMapping("/edit_after_ops_tender")
    public ModelAndView edit_after_ops_tender(
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "tend_status_id", required = false) Long tend_status_id,
            @RequestParam(name = "req_end_date", required = false) String req_tend_end_date,
            @RequestParam(name = "req_result_text", required = false) String req_result_text,
            @RequestParam(name = "req_result_status_id", required = false, defaultValue = "0") Long req_result_status_id,
            @RequestParam(name = "req_profit_predict", required = false) String req_profit_predict,
            @RequestParam(name = "req_result_note", required = false) String req_result_note,
            @RequestParam(name = "req_start_date", required = false) String req_tend_date,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "req_work_note", required = false) String req_work_note,
            @RequestParam(name = "req_doc_number", required = false) String req_doc_number,
            @RequestParam(name = "req_doc_start_date", required = false) String req_doc_start_date,
            @RequestParam(name = "req_doc_end_date", required = false) String req_doc_end_date,
            @RequestParam(name = "req_work_start_date", required = false) String req_work_start_date,
            @RequestParam(name = "tend_fia_status_id", required = false, defaultValue = "0") Long tend_fia_status_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditAfterOpsQuery = entityManager
                .createStoredProcedureQuery("PKG_TENDERS.pr_EditFinalContTendRequest")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(18, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, req_id)
                .setParameter(4, tend_status_id)
                .setParameter(5, req_tend_end_date)
                .setParameter(6, req_result_text)
                .setParameter(7, req_result_status_id)
                .setParameter(8, req_result_status_id)
                .setParameter(9, req_profit_predict)
                .setParameter(10, req_result_note)
                .setParameter(11, req_tend_date)
                .setParameter(12, user_id)
                .setParameter(13, req_work_note)
                .setParameter(14, req_doc_number)
                .setParameter(15, req_doc_start_date)
                .setParameter(16, req_doc_end_date)
                .setParameter(17, req_work_start_date)
                .setParameter(18, tend_fia_status_id)
                ;
        EditAfterOpsQuery.execute();
        return null;
    }

    @PostMapping("/tender_clone")
    public ModelAndView tender_clone(
            @RequestParam(name = "req_id") Long req_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery TenderReqCloneQuery = entityManager
                .createStoredProcedureQuery("PKG_TENDERS.pr_CloneTendContRequest")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, req_id);
        TenderReqCloneQuery.execute();
        return null;
    }

    @PostMapping("/del_tender")
    public ModelAndView del_tender(
            @RequestParam(name = "req_id") Long req_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery TenderReqDeleteQuery = entityManager
                .createStoredProcedureQuery("PKG_TENDERS.pr_DelTendRequest")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, req_id);
        TenderReqDeleteQuery.execute();
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

            result.setData(menuTenderFilesListRepository.queryTenderFilesMenuByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_id
            ));
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

            result.setData(menuTenderChatListRepository.queryRequestChatMenuByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_id
            ));
        }
        return result;
    }

    @RequestMapping("/files_download")
    public ResponseEntity<byte[]> download(@RequestParam(name = "req_store_id") Long req_store_id
    ) throws SQLException {
        TenderFile clientTenderFile = tenderFileRepository.queryByReqStoreCurrFileID(req_store_id);

        int blobLength = (int) clientTenderFile.req_store_file_body.length();
        byte[] output = clientTenderFile.req_store_file_body.getBytes(1, blobLength);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(clientTenderFile.req_store_file_contenttype));
        responseHeaders.setContentLength(output.length);

        try {
            responseHeaders.set("Content-Disposition","attachment; filename="+ URLEncoder.encode(clientTenderFile.req_store_filename, "UTF-8"));
            //responseHeaders.set("Content-disposition", "attachment; filename="+ MimeUtility.encodeWord(clientTenderFile.req_store_filename, "utf-8","Q"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmailServiceImpl javaMailSender;

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

        StoredProcedureQuery AddTenderChatQuery = entityManager
                .createStoredProcedureQuery("PKG_TENDERS.pr_AddTendChat")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT)
                .setParameter(1, req_id)
                .setParameter(2, user_List.get(0).id)
                .setParameter(3, user_Role_List.get(0).role_id)
                .setParameter(4, req_chat_text);
        AddTenderChatQuery.execute();
        Long ReqChatID = (Long) AddTenderChatQuery.getOutputParameterValue(5);

        mail_List = mail_ListRepository.queryRequstChatMail(1L,ReqChatID);
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
            @RequestParam(name = "first_user_id", defaultValue = "0") Long first_user_id,
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
                    .createStoredProcedureQuery("PKG_TENDERS.pr_editSaleUser")
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

            result.setData(menuTenderChatBoxRepository.queryTenderChatBoxByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_id
            ));
        }
        return result;
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

        TenderFile tenderFile = new TenderFile();

        tenderFile.req_id = req_id;
        tenderFile.user_id = user_List.get(0).id;
        tenderFile.req_store_filename = file.getOriginalFilename();
        tenderFile.req_store_file_contenttype = file.getContentType();

        try (InputStream inputStream = file.getInputStream()) {
            byte[] obj_in = IOUtils.toByteArray(inputStream);
            tenderFile.req_store_file_body = new SerialBlob(obj_in);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tenderFile = tenderFileRepository.save(tenderFile);
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
                    .createStoredProcedureQuery("PKG_TENDERS.pr_DelReqFile")
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
}
