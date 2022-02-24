package com.idltd.hydramob.controller.clients_managers;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.clients_managers.MenuClientsManagersListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/clients_managers")
public class ClientsManagersController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClientsManagersListRepository menuClientsManagersListRepository;

    public ClientsManagersController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClientsManagersListRepository menuClientsManagersListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuClientsManagersListRepository = menuClientsManagersListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,

                              @RequestParam(name = "clients_managers_main_table_order_column", required = false) Long clients_managers_main_table_order_column,
                              @RequestParam(name = "clients_managers_main_table_order_type", required = false) String clients_managers_main_table_order_type,
                              @RequestParam(name = "clients_managers_main_table_search", required = false) String clients_managers_main_table_search,
                              @RequestParam(name = "clients_managers_main_table_pagelen", required = false) Long clients_managers_main_table_pagelen,
                              @RequestParam(name = "clients_managers_main_table_page", required = false) Long clients_managers_main_table_page
    ){
        model.addObject("cnt_id", cnt_id);

        model.addObject("clients_managers_main_table_order_column", clients_managers_main_table_order_column);
        model.addObject("clients_managers_main_table_order_type", clients_managers_main_table_order_type);
        model.addObject("clients_managers_main_table_search", clients_managers_main_table_search);
        model.addObject("clients_managers_main_table_pagelen", clients_managers_main_table_pagelen);
        model.addObject("clients_managers_main_table_page", clients_managers_main_table_page);

        model.setViewName("clients_managers/cover");
        return model;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/clients_managers_main")
    public JSONDatatable clients_managers_main(
            @RequestParam(name = "req_cs_user_id", required = false, defaultValue = "-1") Long req_cs_user_id,
            @RequestParam(name = "req_ops_user_id", required = false, defaultValue = "-1") Long req_ops_user_id

    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(req_cs_user_id != null && req_ops_user_id != null  && req_cs_user_id > -1 && req_ops_user_id > -1) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientsManagersListRepository.queryClientManagersMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_cs_user_id, req_ops_user_id
            ));
        }
        return result;
    }

    @GetMapping("/get_sale_list")
    public JSONDatatable get_sale_list(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(user_ListRepository.querySaleUserByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        return result;
    }

    @GetMapping("/get_ops_list")
    public JSONDatatable get_ops_list(
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(user_ListRepository.queryOpsUserByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        return result;
    }

    @PostMapping("/cs_main_del")
    public ModelAndView cs_main_del(
            @RequestParam(name = "cs_user_link_id") Long cs_user_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(cs_user_link_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelClientCsQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelClientCsUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cs_user_link_id);
            DelClientCsQuery.execute();
        }
        return null;
    }

    @PostMapping("/ops_main_del")
    public ModelAndView ops_main_del(
            @RequestParam(name = "ops_user_link_id") Long ops_user_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(ops_user_link_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelClientOPSQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelClientOPSUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, ops_user_link_id);
            DelClientOPSQuery.execute();
        }
        return null;
    }

    @PostMapping("/cnt_main_del")
    public ModelAndView cnt_main_del(
            @RequestParam(name = "cnt_id") Long cnt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(cnt_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelClientCNTQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelClientCNTUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id);
            DelClientCNTQuery.execute();
        }
        return null;
    }

    @PostMapping("/main_edit")
    public ModelAndView main_edit(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "ser_id") Long ser_id,
            @RequestParam(name = "cs_cntul_id", required = false, defaultValue = "0") Long cs_cntul_id,
            @RequestParam(name = "cs_user_id", required = false, defaultValue = "0") Long cs_user_id,
            @RequestParam(name = "cs_persent", required = false, defaultValue = "0") String  cs_persent,
            @RequestParam(name = "ops_cntul_id", required = false, defaultValue = "0") Long ops_cntul_id,
            @RequestParam(name = "ops_user_id", required = false, defaultValue = "0") Long ops_user_id,
            @RequestParam(name = "ops_persent", required = false, defaultValue = "0") String ops_persent
    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        try{
            StoredProcedureQuery EditUsersRolesQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContragentUserLinkWithLog")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, ser_id)
                    .setParameter(5, cs_cntul_id)
                    .setParameter(6, cs_user_id)
                    .setParameter(7, cs_persent)
                    .setParameter(8, ops_cntul_id)
                    .setParameter(9, ops_user_id)
                    .setParameter(10, ops_persent)
                    ;
            EditUsersRolesQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/main_link_edit")
    public ModelAndView main_link_edit(
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
                    .createStoredProcedureQuery("PKG_ADMIN.pr_DelClientCNTUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, first_user_id)
                    .setParameter(4, second_user_id)
                    ;
            DelClientCNTQuery.execute();
        }
        return null;
    }
}
