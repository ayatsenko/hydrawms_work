package com.idltd.hydramob.controller.wms_clients_exchange;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import com.idltd.hydramob.entity.wms_clients_exchange.ExchangeServiceTypeFile;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_clients_exchange.*;
import com.idltd.hydramob.utils.JSONDatatable;
import org.apache.commons.io.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/wms_clients_exchange")
public class WMSClientsExchangeController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuWMSClientsExchangeRepository menuWMSClientsExchangeRepository;
    private ContragentExchangeListRepository contragentExchangeListRepository;
    private ContragentExchangeServiceListRepository contragentExchangeServiceListRepository;
    private ExchangeServiceTypeFileRepository exchangeServiceTypeFileRepository;

    private MenuWMSClientsExchangeLogRepository menuWMSClientsExchangeLogRepository;

    public WMSClientsExchangeController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuWMSClientsExchangeRepository menuWMSClientsExchangeRepository,
            ContragentExchangeListRepository contragentExchangeListRepository,
            ContragentExchangeServiceListRepository contragentExchangeServiceListRepository,
            ExchangeServiceTypeFileRepository exchangeServiceTypeFileRepository,

            MenuWMSClientsExchangeLogRepository menuWMSClientsExchangeLogRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuWMSClientsExchangeRepository = menuWMSClientsExchangeRepository;
        this.contragentExchangeListRepository = contragentExchangeListRepository;
        this.contragentExchangeServiceListRepository = contragentExchangeServiceListRepository;
        this.exchangeServiceTypeFileRepository = exchangeServiceTypeFileRepository;

        this.menuWMSClientsExchangeLogRepository = menuWMSClientsExchangeLogRepository;
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

         model.setViewName("wms_clients_exchange/cover");
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

        result.setData(menuWMSClientsExchangeRepository.queryWMSClientsExchangeByID(
                user_List.get(0).id,
                user_Role_List.get(0).role_id
        ));

        return result;
    }

    @PostMapping("/get_log_menu")
    public JSONDatatable get_log_menu(
            @RequestParam(name = "exch_ser_file_id", required = false) Long exch_ser_file_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(exch_ser_file_id != null && exch_ser_file_id > 0) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSClientsExchangeLogRepository.queryWMSClientsExchangeLogByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    exch_ser_file_id
            ));
        }
        return result;
    }

    @GetMapping("/get_client_exchange_list")
    public JSONDatatable get_client_exchange_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(mode == 0) {
                result.setData(contragentExchangeListRepository.queryExchangeCntList(
                        user_List.get(0).id, user_Role_List.get(0).role_id
                ));
            }
        }
        return result;
    }

    @GetMapping("/get_client_exchange_service_list")
    public JSONDatatable get_client_exchange_service_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id", required = false, defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(mode == 0) {
                result.setData(contragentExchangeServiceListRepository.queryExchangeCntServiceList(
                        user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
                ));
            }
        }
        return result;
    }

    //Add Documents File
    @PostMapping("/add_client_exchange_service_file")
    public ModelAndView add_client_exchange_service_file(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "exch_ser_type_id") Long exch_ser_type_id,
            @RequestParam("file") MultipartFile file
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        ExchangeServiceTypeFile clients_documents_file = new ExchangeServiceTypeFile();

        clients_documents_file.cnt_id = cnt_id;
        clients_documents_file.user_id = user_List.get(0).id;
        clients_documents_file.role_id = user_Role_List.get(0).role_id;
        clients_documents_file.exch_ser_file_name = file.getOriginalFilename();
        clients_documents_file.exch_ser_file_contenttype = file.getContentType();
        clients_documents_file.exch_ser_type_id = exch_ser_type_id;

        try (InputStream inputStream = file.getInputStream()) {
            byte[] obj_in = IOUtils.toByteArray(inputStream);
            clients_documents_file.exch_ser_file_body = new javax.sql.rowset.serial.SerialBlob(obj_in);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clients_documents_file = exchangeServiceTypeFileRepository.save(clients_documents_file);
        return null;
    }

    @PostMapping("/del_client_exchange_service_file")
    public ModelAndView del_client_exchange_service_file(
            @RequestParam(name = "exch_ser_file_id") Long exch_ser_file_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_DelWHClientExchangeFile")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, exch_ser_file_id);
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
}
