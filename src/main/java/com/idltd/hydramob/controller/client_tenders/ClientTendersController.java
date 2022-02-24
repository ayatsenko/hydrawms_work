package com.idltd.hydramob.controller.client_tenders;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.client_tenders.DetailClientTenderFilesList;
import com.idltd.hydramob.entity.client_tenders.DetailClientTenderList;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.client_tenders.DetailClientTenderFilesListRepository;
import com.idltd.hydramob.repository.client_tenders.DetailClientTenderListRepository;
import com.idltd.hydramob.repository.client_tenders.MenuClientTenderFilesListRepository;
import com.idltd.hydramob.repository.client_tenders.MenuClientTendersListRepository;
import com.idltd.hydramob.repository.tenders.MenuTenderChatListRepository;
import com.idltd.hydramob.repository.tenders.TenderFileRepository;
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
@RequestMapping("/client_tenders")
public class ClientTendersController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClientTendersListRepository menuClientTendersListRepository;
    private DetailClientTenderListRepository detailClientTenderListRepository;
    private Request_Result_Status_ListRepository request_Result_Status_ListRepository;
    
    private MenuTenderChatListRepository menuTenderChatListRepository;
    private TenderFileRepository tenderFileRepository;

    private DetailClientTenderFilesListRepository detailClientTenderFilesListRepository;
    private Mail_ListRepository mail_ListRepository;

    private MenuClientTenderFilesListRepository menuClientTenderFilesListRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private Request_Type_ListRepository request_Type_ListRepository;
    private Tender_Status_ListRepository tender_Status_ListRepository;
    private Request_Tend_Status_ListRepository request_Tend_Status_ListRepository;

    public ClientTendersController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClientTendersListRepository menuClientTendersListRepository,
            DetailClientTenderListRepository detailClientTenderListRepository,
            Request_Result_Status_ListRepository request_Result_Status_ListRepository,
                    
            MenuTenderChatListRepository menuTenderChatListRepository,
            DetailClientTenderFilesListRepository detailClientTenderFilesListRepository,
            Mail_ListRepository mail_ListRepository,

            MenuClientTenderFilesListRepository menuClientTenderFilesListRepository,
            TenderFileRepository tenderFileRepository,
            Contragent_ListRepository contragent_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Request_Type_ListRepository request_Type_ListRepository,
            Tender_Status_ListRepository tender_Status_ListRepository,
            Request_Tend_Status_ListRepository request_Tend_Status_ListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuClientTendersListRepository = menuClientTendersListRepository;
        this.detailClientTenderListRepository = detailClientTenderListRepository;
        this.request_Result_Status_ListRepository = request_Result_Status_ListRepository;
        
        this.menuTenderChatListRepository = menuTenderChatListRepository;
        this.detailClientTenderFilesListRepository = detailClientTenderFilesListRepository;
        this.mail_ListRepository = mail_ListRepository;

        this.menuClientTenderFilesListRepository = menuClientTenderFilesListRepository;
        this.tenderFileRepository = tenderFileRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.request_Type_ListRepository = request_Type_ListRepository;
        this.tender_Status_ListRepository = tender_Status_ListRepository;
        this.request_Tend_Status_ListRepository = request_Tend_Status_ListRepository;
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

                              @RequestParam(name = "client_tenders_list_table_order_column", required = false) Long client_tenders_list_table_order_column,
                              @RequestParam(name = "client_tenders_list_table_order_type", required = false) String client_tenders_list_table_order_type,
                              @RequestParam(name = "client_tenders_list_table_search", required = false) String client_tenders_list_table_search,
                              @RequestParam(name = "client_tenders_list_table_pagelen", required = false) Long client_tenders_list_table_pagelen,
                              @RequestParam(name = "client_tenders_list_table_page", required = false) Long client_tenders_list_table_page,

                              @RequestParam(name = "client_tenders_filter_id", required = false) Long client_tenders_filter_id,
                              @RequestParam(name = "client_tenders_filter_start_date", required = false) String client_tenders_filter_start_date,
                              @RequestParam(name = "client_tenders_filter_end_date", required = false) String client_tenders_filter_end_date                              
    ){
        model.addObject("req_id", req_id);
        model.addObject("req_store_id", req_store_id);

        model.addObject("cnt_id", cnt_id);

        model.addObject("cnt_name", cnt_name);
        model.addObject("cnt_user_access", cnt_user_access);

        model.addObject("client_tenders_list_table_order_column", client_tenders_list_table_order_column);
        model.addObject("client_tenders_list_table_order_type", client_tenders_list_table_order_type);
        model.addObject("client_tenders_list_table_search", client_tenders_list_table_search);
        model.addObject("client_tenders_list_table_pagelen", client_tenders_list_table_pagelen);
        model.addObject("client_tenders_list_table_page", client_tenders_list_table_page);

        model.addObject("clients_list_table_order_column", clients_list_table_order_column);
        model.addObject("clients_list_table_order_type", clients_list_table_order_type);
        model.addObject("clients_list_table_search", clients_list_table_search);
        model.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        model.addObject("clients_list_table_page", clients_list_table_page);

        model.addObject("client_tenders_filter_id", client_tenders_filter_id);
        model.addObject("client_tenders_filter_start_date", client_tenders_filter_start_date);
        model.addObject("client_tenders_filter_end_date", client_tenders_filter_end_date);        
        
        model.setViewName("client_tenders/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,

            @RequestParam(name = "client_tenders_filter_id", required = false, defaultValue = "0") Long client_tenders_filter_id,
            @RequestParam(name = "client_tenders_filter_start_date", required = false) String client_tenders_filter_start_date,
            @RequestParam(name = "client_tenders_filter_end_date", required = false) String client_tenders_filter_end_date            
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuClientTendersListRepository.queryClientTendersMenuListByUserID(user_List.get(0).id,user_Role_List.get(0).role_id, cnt_id, client_tenders_filter_id, client_tenders_filter_start_date, client_tenders_filter_end_date));

        return result;
    }

    @RequestMapping("/list_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="req_id") Long req_id,
                                   @RequestParam(value="user_id") Long user_id,
                                   @RequestParam(value="tend_status_id") Long tend_status_id,

                                   @RequestParam(value = "cnt_id") Long cnt_id,
                                   @RequestParam(value = "cnt_name", required = false) String cnt_name,
                                   @RequestParam(value = "cnt_user_access", required = false) Long cnt_user_access,

                                   @RequestParam(value="client_tenders_list_table_order_column") Long order_column,
                                   @RequestParam(value="client_tenders_list_table_order_type") String order_type,
                                   @RequestParam(value="client_tenders_list_table_search") String table_search,
                                   @RequestParam(value="client_tenders_list_table_pagelen") Long pagelen,
                                   @RequestParam(value="client_tenders_list_table_page") Long page,

                                   @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                   @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                   @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                   @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                   @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                   @RequestParam(name = "client_tenders_filter_id", required = false) Long client_tenders_filter_id,
                                   @RequestParam(name = "client_tenders_filter_start_date", required = false) String client_tenders_filter_start_date,
                                   @RequestParam(name = "client_tenders_filter_end_date", required = false) String client_tenders_filter_end_date
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<DetailClientTenderList> userEditTenderRequest;
        List<DetailClientTenderList> managerMiddleEditList;
        List<DetailClientTenderList> managerOpsEditList;
        List<DetailClientTenderList> managerAfterOpsEditList;
        List<DetailClientTenderList> managerAfterAllEditList;

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Contragent_List> contragent_List;
        List<Service_Type_List> service_Type_List;
        List<Request_Type_List> request_Type_List;
        List<User_List> sale_User_List;

        List<Tender_Status_List> tender_Status_List;
        List<Request_Tend_Status_List> request_Tend_Status_List;
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

        if(mode == 0) {
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            service_Type_List = (List<Service_Type_List>) service_Type_ListRepository.findAll();
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", new Long(2));
            request_Type_List = request_Type_ListRepository.queryTypeByID(new Long(2));
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("user_id", user_List.get(0).id);
            sale_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("tend_status_id", new Long(2));
            tender_Status_List = tender_Status_ListRepository.queryNewEditStatusID();
            mav.addObject("tend_status_list", tender_Status_List);

            request_Tend_Status_List = request_Tend_Status_ListRepository.queryNewTendStatusID();
            mav.addObject("req_result_status_list", request_Tend_Status_List);

            mav.addObject("req_profit_predict", 0);
            /*Редактирование тендерного запроса Менеджером*/
        }
        else if(mode == 1
                    && ((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                    || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                    && user_List.get(0).id.intValue() == user_id.intValue())
                    || user_Role_List.get(0).role_id == 8)
                    && (tend_status_id == 1 || tend_status_id == 2)
            ){
                userEditTenderRequest = detailClientTenderListRepository.queryClientTenderDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

                mav.addObject("req_id", userEditTenderRequest.get(0).req_id);
                mav.addObject("req_name", userEditTenderRequest.get(0).req_name);

                mav.addObject("cnt_id", cnt_id);
                mav.addObject("cnt_name", cnt_name);

                mav.addObject("ser_id", userEditTenderRequest.get(0).ser_id);
                service_Type_List = service_Type_ListRepository.queryGetByID(userEditTenderRequest.get(0).ser_id);
                mav.addObject("ser_list", service_Type_List);

                mav.addObject("req_type_id", userEditTenderRequest.get(0).req_type_id);
                request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
                mav.addObject("req_type_list", request_Type_List);

                mav.addObject("user_id", userEditTenderRequest.get(0).user_id);
                sale_User_List = user_ListRepository.queryUserByID(userEditTenderRequest.get(0).user_id);
                mav.addObject("sale_user_list", sale_User_List);

                mav.addObject("req_note", userEditTenderRequest.get(0).req_note);

                mav.addObject("req_tend_date", userEditTenderRequest.get(0).req_tend_date);

                mav.addObject("req_tend_end_date", userEditTenderRequest.get(0).req_tend_end_date);

                mav.addObject("req_tend_runway", userEditTenderRequest.get(0).req_tend_runway);

                mav.addObject("req_tend_autotype", userEditTenderRequest.get(0).req_tend_autotype);

                mav.addObject("req_tend_count", userEditTenderRequest.get(0).req_tend_count);

                mav.addObject("req_ops_id", userEditTenderRequest.get(0).req_ops_id);

                mav.addObject("req_ops_date", userEditTenderRequest.get(0).req_ops_date);

                mav.addObject("req_ops_answer", userEditTenderRequest.get(0).req_ops_answer);

                mav.addObject("req_ops_note", userEditTenderRequest.get(0).req_ops_note);

                if(userEditTenderRequest.get(0).req_ops_user_id != null) {
                    mav.addObject("req_ops_user_id", userEditTenderRequest.get(0).req_ops_user_id);
                    ops_User_List = user_ListRepository.queryUserByID(userEditTenderRequest.get(0).req_ops_user_id);
                    mav.addObject("ops_user_list", ops_User_List);
                }
                mav.addObject("tend_status_id", userEditTenderRequest.get(0).tend_status_id);
                if(user_Role_List.get(0).role_id == 8){
                    tender_Status_List = tender_Status_ListRepository.queryFinishTendEditStatusID();
                }
                else {
                    tender_Status_List = tender_Status_ListRepository.queryNewEditStatusID();
                }
                mav.addObject("tend_status_list", tender_Status_List);

                mav.addObject("req_result_status_id", userEditTenderRequest.get(0).req_result_status_id);
                request_Tend_Status_List = (List<Request_Tend_Status_List>) request_Tend_Status_ListRepository.findAll();
                mav.addObject("req_result_status_list", request_Tend_Status_List);

                mav.addObject("req_profit_predict", userEditTenderRequest.get(0).req_profit_predict);
            }
/*Редактирование запроса во время OPS*/
        else if(mode == 1
                && ((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                && user_List.get(0).id.intValue() == user_id.intValue())
                || user_Role_List.get(0).role_id == 8)
                && (tend_status_id == 3)
        ){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            managerMiddleEditList = detailClientTenderListRepository.queryClientTenderDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

            mav.addObject("ser_id", managerMiddleEditList.get(0).ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(managerMiddleEditList.get(0).ser_id);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("req_type_id", managerMiddleEditList.get(0).req_type_id);
            request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
            mav.addObject("req_type_list", request_Type_List);

            mav.addObject("req_id", managerMiddleEditList.get(0).req_id);

            mav.addObject("user_id", managerMiddleEditList.get(0).user_id);
            sale_User_List = user_ListRepository.queryUserByID(managerMiddleEditList.get(0).user_id);
            mav.addObject("sale_user_list", sale_User_List);

            mav.addObject("req_name", managerMiddleEditList.get(0).req_name);

            mav.addObject("req_note", managerMiddleEditList.get(0).req_note);

            mav.addObject("req_tend_date", managerMiddleEditList.get(0).req_tend_date);

            mav.addObject("req_tend_end_date", managerMiddleEditList.get(0).req_tend_end_date);

            mav.addObject("req_tend_runway", managerMiddleEditList.get(0).req_tend_runway);

            mav.addObject("req_tend_autotype", managerMiddleEditList.get(0).req_tend_autotype);

            mav.addObject("req_tend_count", managerMiddleEditList.get(0).req_tend_count);

            mav.addObject("req_ops_id", managerMiddleEditList.get(0).req_ops_id);

            mav.addObject("req_ops_date", managerMiddleEditList.get(0).req_ops_date);

            mav.addObject("req_ops_answer", managerMiddleEditList.get(0).req_ops_answer);

            mav.addObject("req_ops_note", managerMiddleEditList.get(0).req_ops_note);

            if(managerMiddleEditList.get(0).req_ops_user_id != null && managerMiddleEditList.get(0).req_ops_user_id > 0) {
                mav.addObject("req_ops_user_id", managerMiddleEditList.get(0).req_ops_user_id);
                ops_User_List = user_ListRepository.queryUserByID(managerMiddleEditList.get(0).req_ops_user_id);
                mav.addObject("ops_user_list", ops_User_List);
            }

            mav.addObject("tend_status_id", managerMiddleEditList.get(0).tend_status_id);
            tender_Status_List = (List<Tender_Status_List>) tender_Status_ListRepository.findAll();
            mav.addObject("tend_status_list", tender_Status_List);

            mav.addObject("req_result_text", managerMiddleEditList.get(0).req_result_text);

            mav.addObject("req_result_status_id", managerMiddleEditList.get(0).req_result_status_id);
            request_Result_Status_List = request_Result_Status_ListRepository.queryEndResultStatusAll();
            mav.addObject("req_result_status_list", request_Result_Status_List);

            mav.addObject("req_result_status_id", managerMiddleEditList.get(0).req_tend_status_id);
            request_Tend_Status_List = (List<Request_Tend_Status_List>) request_Tend_Status_ListRepository.findAll();
            mav.addObject("req_result_status_list", request_Tend_Status_List);

            mav.addObject("req_profit_predict", managerMiddleEditList.get(0).req_profit_predict);

            mav.addObject("req_result_note", managerMiddleEditList.get(0).req_result_note);
        }
/*Редактирование тендерного запроса OPS*/
            else if(mode == 1
                    && (user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                    && userID.intValue() != user_id.intValue() && (tend_status_id == 2 || tend_status_id == 3 || tend_status_id == 4)
            ){
                managerOpsEditList = detailClientTenderListRepository.queryClientTenderDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

                mav.addObject("cnt_id", cnt_id);
                mav.addObject("cnt_name", cnt_name);

                mav.addObject("ser_id", managerOpsEditList.get(0).ser_id);
                service_Type_List = service_Type_ListRepository.queryGetByID(managerOpsEditList.get(0).ser_id);
                mav.addObject("ser_list", service_Type_List);

                mav.addObject("req_type_id", managerOpsEditList.get(0).req_type_id);
                request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
                mav.addObject("req_type_list", request_Type_List);

                mav.addObject("req_id", managerOpsEditList.get(0).req_id);

                mav.addObject("user_id", managerOpsEditList.get(0).user_id);
                sale_User_List = user_ListRepository.queryUserByID(managerOpsEditList.get(0).user_id);
                mav.addObject("sale_user_list", sale_User_List);

                mav.addObject("req_name", managerOpsEditList.get(0).req_name);

                mav.addObject("req_note", managerOpsEditList.get(0).req_note);

                mav.addObject("req_tend_date", managerOpsEditList.get(0).req_tend_date);

                mav.addObject("req_tend_end_date", managerOpsEditList.get(0).req_tend_end_date);

                mav.addObject("req_tend_runway", managerOpsEditList.get(0).req_tend_runway);

                mav.addObject("req_tend_autotype", managerOpsEditList.get(0).req_tend_autotype);

                mav.addObject("req_tend_count", managerOpsEditList.get(0).req_tend_count);

                mav.addObject("req_ops_id", managerOpsEditList.get(0).req_ops_id);

                mav.addObject("req_ops_date", managerOpsEditList.get(0).req_ops_date);

                mav.addObject("req_ops_answer", managerOpsEditList.get(0).req_ops_answer);

                mav.addObject("req_ops_note", managerOpsEditList.get(0).req_ops_note);

                if(managerOpsEditList.get(0).tend_status_id == 2) {
                    mav.addObject("tend_status_id", new Long(4));
                }else{
                    mav.addObject("tend_status_id", managerOpsEditList.get(0).tend_status_id);
                }
                tender_Status_List = tender_Status_ListRepository.queryOpsEditStatusID();
                mav.addObject("tend_status_list", tender_Status_List);

                mav.addObject("req_result_status_id", managerOpsEditList.get(0).req_result_status_id);

                mav.addObject("req_profit_predict", managerOpsEditList.get(0).req_profit_predict);
            }
/*Редактирование тендерного запроса после OPS*/
            else if(mode == 1
                    && (userID.intValue() == user_id.intValue()
                    || user_Role_List.get(0).role_id == 8)
                    && (tend_status_id == 4 || tend_status_id == 5 || tend_status_id == 6)
            ){
                managerAfterOpsEditList = detailClientTenderListRepository.queryClientTenderDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

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

                mav.addObject("req_note", managerAfterOpsEditList.get(0).req_note);

                mav.addObject("req_tend_date", managerAfterOpsEditList.get(0).req_tend_date);

                mav.addObject("req_tend_end_date", managerAfterOpsEditList.get(0).req_tend_end_date);

                mav.addObject("req_tend_runway", managerAfterOpsEditList.get(0).req_tend_runway);

                mav.addObject("req_tend_autotype", managerAfterOpsEditList.get(0).req_tend_autotype);

                mav.addObject("req_tend_count", managerAfterOpsEditList.get(0).req_tend_count);

                mav.addObject("req_ops_id", managerAfterOpsEditList.get(0).req_ops_id);

                mav.addObject("req_ops_date", managerAfterOpsEditList.get(0).req_ops_date);

                mav.addObject("req_ops_answer", managerAfterOpsEditList.get(0).req_ops_answer);

                mav.addObject("req_ops_note", managerAfterOpsEditList.get(0).req_ops_note);

                if(managerAfterOpsEditList.get(0).req_ops_user_id != null && managerAfterOpsEditList.get(0).req_ops_user_id > 0) {
                    mav.addObject("req_ops_user_id", managerAfterOpsEditList.get(0).req_ops_user_id);
                    ops_User_List = user_ListRepository.queryUserByID(managerAfterOpsEditList.get(0).req_ops_user_id);
                    mav.addObject("ops_user_list", ops_User_List);
                }

                mav.addObject("tend_status_id", new Long(5));
                tender_Status_List = tender_Status_ListRepository.queryFinishTendEditStatusID();
                mav.addObject("tend_status_list", tender_Status_List);

                mav.addObject("req_result_text", managerAfterOpsEditList.get(0).req_result_text);

                mav.addObject("req_result_status_id", managerAfterOpsEditList.get(0).req_result_status_id);
                request_Result_Status_List =  request_Result_Status_ListRepository.queryEndResultStatusAll();
                mav.addObject("req_result_status_list", request_Result_Status_List);

                mav.addObject("req_result_status_id", managerAfterOpsEditList.get(0).req_tend_status_id);
                request_Tend_Status_List = request_Tend_Status_ListRepository.queryFinalTendStatusID();
                mav.addObject("req_result_status_list", request_Tend_Status_List);

                mav.addObject("req_profit_predict", managerAfterOpsEditList.get(0).req_profit_predict);

                mav.addObject("req_result_note", managerAfterOpsEditList.get(0).req_result_note);
            }
            else if((mode == 2 || mode == 4))
            {
                managerAfterAllEditList = detailClientTenderListRepository.queryClientTenderDetailByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_id);

                mav.addObject("cnt_id", cnt_id);
                mav.addObject("cnt_name", cnt_name);

                mav.addObject("ser_id", managerAfterAllEditList.get(0).ser_id);
                service_Type_List = service_Type_ListRepository.queryGetByID(managerAfterAllEditList.get(0).ser_id);
                mav.addObject("ser_list", service_Type_List);

                mav.addObject("req_type_id", managerAfterAllEditList.get(0).req_type_id);
                request_Type_List = (List<Request_Type_List>) request_Type_ListRepository.findAll();
                mav.addObject("req_type_list", request_Type_List);

                mav.addObject("req_id", managerAfterAllEditList.get(0).req_id);

                mav.addObject("user_id", managerAfterAllEditList.get(0).user_id);
                sale_User_List = user_ListRepository.queryUserByID(managerAfterAllEditList.get(0).user_id);
                mav.addObject("sale_user_list", sale_User_List);

                mav.addObject("req_name", managerAfterAllEditList.get(0).req_name);

                mav.addObject("req_note", managerAfterAllEditList.get(0).req_note);

                mav.addObject("req_tend_date", managerAfterAllEditList.get(0).req_tend_date);

                mav.addObject("req_tend_end_date", managerAfterAllEditList.get(0).req_tend_end_date);

                mav.addObject("req_tend_runway", managerAfterAllEditList.get(0).req_tend_runway);

                mav.addObject("req_tend_autotype", managerAfterAllEditList.get(0).req_tend_autotype);

                mav.addObject("req_tend_count", managerAfterAllEditList.get(0).req_tend_count);

                mav.addObject("req_ops_id", managerAfterAllEditList.get(0).req_ops_id);

                mav.addObject("req_ops_date", managerAfterAllEditList.get(0).req_ops_date);

                mav.addObject("req_ops_answer", managerAfterAllEditList.get(0).req_ops_answer);

                mav.addObject("req_ops_note", managerAfterAllEditList.get(0).req_ops_note);

                if(managerAfterAllEditList.get(0).req_ops_user_id != null && managerAfterAllEditList.get(0).req_ops_user_id > 0) {
                    mav.addObject("req_ops_user_id", managerAfterAllEditList.get(0).req_ops_user_id);
                    ops_User_List = user_ListRepository.queryUserByID(managerAfterAllEditList.get(0).req_ops_user_id);
                    mav.addObject("ops_user_list", ops_User_List);
                }

                mav.addObject("tend_status_id", managerAfterAllEditList.get(0).tend_status_id);
                tender_Status_List = (List<Tender_Status_List>) tender_Status_ListRepository.findAll();
                mav.addObject("tend_status_list", tender_Status_List);

                mav.addObject("req_result_text", managerAfterAllEditList.get(0).req_result_text);

                mav.addObject("req_result_status_id", managerAfterAllEditList.get(0).req_result_status_id);
                request_Result_Status_List =  request_Result_Status_ListRepository.queryEndResultStatusAll();
                mav.addObject("req_result_status_list", request_Result_Status_List);

                mav.addObject("req_result_status_id", managerAfterAllEditList.get(0).req_tend_status_id);
                request_Tend_Status_List = (List<Request_Tend_Status_List>) request_Tend_Status_ListRepository.findAll();
                mav.addObject("req_result_status_list", request_Tend_Status_List);

                mav.addObject("req_profit_predict", managerAfterAllEditList.get(0).req_profit_predict);

                mav.addObject("req_result_note", managerAfterAllEditList.get(0).req_result_note);
            }
            else {
            }

        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);
            
        mav.addObject("client_tenders_list_table_order_column", order_column);
        mav.addObject("client_tenders_list_table_order_type", order_type);
        mav.addObject("client_tenders_list_table_search", table_search);
        mav.addObject("client_tenders_list_table_pagelen", pagelen);
        mav.addObject("client_tenders_list_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("client_tenders_filter_id", client_tenders_filter_id);
        mav.addObject("client_tenders_filter_start_date", client_tenders_filter_start_date);
        mav.addObject("client_tenders_filter_end_date", client_tenders_filter_end_date);

        mav.setViewName("/client_tenders/list_detail");
        return mav;
    }

    @PostMapping("/list_model")
    public ModelAndView list_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false, defaultValue = "1") Long cnt_id,
            @RequestParam(name = "req_id", required = false) Long req_id,
            @RequestParam(name = "req_type_id", required = false) Long req_type_id,
            @RequestParam(name = "req_name", required = false) String req_name,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "user_id", required = false, defaultValue = "9") Long user_id,

            @RequestParam(name = "req_note", required = false) String req_note,
            @RequestParam(name = "tend_status_id", required = false) Long tend_status_id,
            @RequestParam(name = "req_result_status_id", required = false, defaultValue = "0") Long req_tend_status_id,

            @RequestParam(name = "req_start_date", required = false) String req_tend_date,
            @RequestParam(name = "req_end_date", required = false) String req_tend_end_date,
            @RequestParam(name = "req_tend_runway", required = false) String req_tend_runway,
            @RequestParam(name = "req_tend_autotype", required = false) String req_tend_autotype,
            @RequestParam(name = "req_tend_count", required = false) String req_tend_count,

            @RequestParam(name = "req_ops_answer", required = false) String req_ops_answer,
            @RequestParam(name = "req_ops_note", required = false) String req_ops_note,

            @RequestParam(name = "req_result_text", required = false) String req_result_text,
            @RequestParam(name = "req_result_note", required = false) String req_result_note,

            @RequestParam(name = "req_profit_predict", required = false) String req_profit_predict,

            @RequestParam(value="client_tenders_list_table_order_column", required = false) Long client_tenders_list_table_order_column,
            @RequestParam(value="client_tenders_list_table_order_type", required = false) String client_tenders_list_table_order_type,
            @RequestParam(value="client_tenders_list_table_search", required = false) String client_tenders_list_table_search,
            @RequestParam(value="client_tenders_list_table_pagelen", required = false) Long client_tenders_list_table_pagelen,
            @RequestParam(value="client_tenders_list_table_page", required = false) Long client_tenders_list_table_page,

            @RequestParam(value = "cnt_name", required = false) String cnt_name,
            @RequestParam(value = "cnt_user_access", required = false) Long cnt_user_access,

            @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(name = "client_tenders_filter_id", required = false) Long client_tenders_filter_id,
            @RequestParam(name = "client_tenders_filter_start_date", required = false) String client_tenders_filter_start_date,
            @RequestParam(name = "client_tenders_filter_end_date", required = false) String client_tenders_filter_end_date

    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Mail_List> mail_List;
        List<DetailClientTenderFilesList> detailClientTenderFilesList;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String FormatTendDate = "";
        String FormatTendEndDate = "";
        Date Date1 = null;
        Date Date3 = null;

        if (req_tend_date != "" && req_tend_date != null) {
            String TendDate = req_tend_date;
            Date1 = df.parse(TendDate);
            FormatTendDate = newdf.format(Date1);
        }

        if (req_tend_end_date != "" && req_tend_end_date != null) {
            String TendEndDate = req_tend_end_date;
            Date3 = df.parse(TendEndDate);
            FormatTendEndDate = newdf.format(Date3);
        }

        int i = 0;

        try {
            TenderFile request_file = new TenderFile();
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddTendQuery = entityManager
                            .createStoredProcedureQuery("PKG_REQUEST.pr_AddTendContRequestRole")
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
                            .setParameter(8, FormatTendDate)
                            .setParameter(9, FormatTendEndDate)
                            .setParameter(10, req_tend_runway)
                            .setParameter(11, req_tend_autotype)
                            .setParameter(12, req_tend_count)
                            .setParameter(13, tend_status_id)
                            .setParameter(14, req_profit_predict);
                    AddTendQuery.execute();

                    Long ReqID = (Long) AddTendQuery.getOutputParameterValue(15);
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
/*Редактирование запроса Менеджером*/
                    if (((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                            || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                            && user_List.get(0).id == user_id)
                            || user_Role_List.get(0).role_id == 8)
                            && (tend_status_id == 1 || tend_status_id == 2)
                    ) {
                        StoredProcedureQuery EditTendQuery = entityManager
                                .createStoredProcedureQuery("PKG_REQUEST.pr_EditContTendRequestRole")
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
                                .setParameter(8, FormatTendDate)
                                .setParameter(9, FormatTendEndDate)
                                .setParameter(10, req_tend_runway)
                                .setParameter(11, req_tend_autotype)
                                .setParameter(12, req_tend_count)
                                .setParameter(13, tend_status_id)
                                .setParameter(14, req_tend_status_id)
                                .setParameter(15, req_profit_predict);
                        EditTendQuery.execute();
                    }
/*Редактирование запроса Менеджером*/
                       else if (((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                                || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                                && user_List.get(0).id == user_id)
                                || user_Role_List.get(0).role_id == 8)
                                && (tend_status_id == 3)
                        ) {
                            StoredProcedureQuery EditMiddleTendQuery = entityManager
                                    .createStoredProcedureQuery("PKG_REQUEST.pr_EditMiddleContTendRequestRole")
                                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                                    .setParameter(1, user_List.get(0).id)
                                    .setParameter(2, user_Role_List.get(0).role_id)
                                    .setParameter(3, req_id)
                                    .setParameter(4, FormatTendDate)
                                    .setParameter(5, FormatTendEndDate)
                                    .setParameter(6, req_note);
                            EditMiddleTendQuery.execute();
                    }
/*Обработка запроса OPS*/
                       else if ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                            && user_List.get(0).id != user_id
                            && (tend_status_id == 2 || tend_status_id == 3 || tend_status_id == 4)) {
                        StoredProcedureQuery EditOpsTendQuery = entityManager
                                .createStoredProcedureQuery("PKG_REQUEST.pr_EditOpsContTendRequestRole")
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
/*Обработка запроса после OPS*/
                    } else if (((user_Role_List.get(0).role_id != 3 && user_Role_List.get(0).role_id != 6 && user_Role_List.get(0).role_id != 9)
                            || ((user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9)
                            && user_List.get(0).id == user_id)
                            || user_Role_List.get(0).role_id == 8)
                            && (tend_status_id == 4 || tend_status_id == 5 || tend_status_id == 6)) {
                        StoredProcedureQuery EditAfterOpsQuery = entityManager
                                .createStoredProcedureQuery("PKG_REQUEST.pr_EditFinalContTendRequestRole")
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
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, req_id)
                                .setParameter(4, tend_status_id)
                                .setParameter(5, FormatTendEndDate)
                                .setParameter(6, req_result_text)
                                .setParameter(7, req_tend_status_id)
                                .setParameter(8, req_tend_status_id)
                                .setParameter(9, req_profit_predict)
                                .setParameter(10, req_result_note)
                                .setParameter(11, FormatTendDate);
                        EditAfterOpsQuery.execute();
                    } else {
                    }

                    mail_List = mail_ListRepository.queryRequstMail(Long.valueOf(2), req_id);
                    if(mail_List.size() > 0) {
                        do {
                            javaMailSender.sendSimpleMessage(mail_List.get(i).user_mail, mail_List.get(i).mail_subject, mail_List.get(i).mail_text);
                            i++;
                        }
                        while (i < mail_List.size());
                    }
                    break;
                case 2:
                    StoredProcedureQuery DelQuery = entityManager
                            .createStoredProcedureQuery("PKG_REQUEST.pr_DelTendRequestRole")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, req_id);
                    DelQuery.execute();

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
        mav.addObject("req_id", req_id);
        mav.addObject("cnt_id", cnt_id);

        mav.addObject("client_tenders_list_table_order_column", client_tenders_list_table_order_column);
        mav.addObject("client_tenders_list_table_order_type", client_tenders_list_table_order_type);
        mav.addObject("client_tenders_list_table_search", client_tenders_list_table_search);
        mav.addObject("client_tenders_list_table_pagelen", client_tenders_list_table_pagelen);
        mav.addObject("client_tenders_list_table_page", client_tenders_list_table_page);

        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("client_tenders_filter_id", client_tenders_filter_id);
        mav.addObject("client_tenders_filter_start_date", client_tenders_filter_start_date);
        mav.addObject("client_tenders_filter_end_date", client_tenders_filter_end_date);

        mav.setViewName("/client_tenders/list_detail");
        return mav;
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

            result.setData(menuClientTenderFilesListRepository.queryClientTenderFilesMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, req_id));
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

            result.setData(menuTenderChatListRepository.queryRequestChatMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, req_id));
        }
        return result;
    }

    @RequestMapping("/files_download")
    public ResponseEntity<byte[]> download(@RequestParam(name = "req_store_id") Long req_store_id
    ) throws SQLException {
        TenderFile clientTenderFile = (TenderFile) tenderFileRepository.queryByReqStoreFileID(req_store_id);

        int blobLength = (int) clientTenderFile.req_store_file_body.length();
        byte[] output = clientTenderFile.req_store_file_body.getBytes(1, blobLength);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(clientTenderFile.req_store_file_contenttype));
        responseHeaders.setContentLength(output.length);
        try {
            responseHeaders.set("Content-disposition", "attachment; filename="+ MimeUtility.encodeWord(clientTenderFile.req_store_filename, "utf-8","Q")
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
        List<DetailClientTenderFilesList> detailClientTenderFilesList;

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
            detailClientTenderFilesList = detailClientTenderFilesListRepository.queryClientTenderFileDetailByID(user_List.get(0).id, user_Role_List.get(0).role_id, req_store_id);

            mav.addObject("req_id", detailClientTenderFilesList.get(0).req_id);

            mav.addObject("req_name",  detailClientTenderFilesList.get(0).req_name);

            mav.addObject("req_store_id",  detailClientTenderFilesList.get(0).req_store_id);

            mav.addObject("req_store_date",  detailClientTenderFilesList.get(0).req_store_date);

            mav.addObject("user_id",  detailClientTenderFilesList.get(0).user_id);
            store_user_List = user_ListRepository.queryUserByID( detailClientTenderFilesList.get(0).user_id);
            mav.addObject("store_user_list", store_user_List);

            mav.addObject("req_store_filename",  detailClientTenderFilesList.get(0).req_store_filename);
        }

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);        
        
        mav.setViewName("/client_tenders/files_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/files_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="req_id") Long req_id,
            @RequestParam(value="req_name", required = false) String req_name,
            @RequestParam(value="req_store_id", required = false) Long req_store_id,
            @RequestParam(value="req_store_date", required = false) String req_store_date,
            @RequestParam("file") MultipartFile file,

            @RequestParam(value="client_tenders_files_table_order_column", required = false) Long order_column,
            @RequestParam(value="client_tenders_files_table_order_type", required = false) String order_type,
            @RequestParam(value="client_tenders_files_table_search", required = false) String table_search,
            @RequestParam(value="client_tenders_files_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="client_tenders_files_table_page", required = false) Long page,

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
        List<DetailClientTenderFilesList> detailClientTenderFilesList;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);

        try {
            TenderFile tender_file = new TenderFile();
            switch (mode.intValue()) {
                case 0:
                    tender_file.req_id = req_id;
                    tender_file.req_store_id = req_store_id;
                    tender_file.user_id = user_List.get(0).id;
                    tender_file.req_store_filename = file.getOriginalFilename();
                    tender_file.req_store_file_contenttype = file.getContentType();

                    try (InputStream inputStream = file.getInputStream()) {
                        byte[] obj_in = IOUtils.toByteArray(inputStream);
                        tender_file.req_store_file_body = new SerialBlob(obj_in);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SerialException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    tender_file = tenderFileRepository.save(tender_file);
                    break;
                case 2:
                    StoredProcedureQuery DelTenderFileQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_DelReqFile")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .setParameter(1, req_store_id);
                    DelTenderFileQuery.execute();
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

        mav.addObject("client_tenders_files_table_order_column", order_column);
        mav.addObject("client_tenders_files_table_order_type", order_type);
        mav.addObject("client_tenders_files_table_search", table_search);
        mav.addObject("client_tenders_files_table_pagelen", pagelen);
        mav.addObject("client_tenders_files_table_page", page);

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("cnt_user_access", cnt_user_access);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.setViewName("/client_tenders/files_detail");
        return mav;
    }

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
        AddTenderChatQuery.execute();
        Long ReqChatID = (Long) AddTenderChatQuery.getOutputParameterValue(5);

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
}
