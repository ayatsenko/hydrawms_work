package com.idltd.hydramob.controller.client_action;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.client_actions.DetailClientActionsRepository;
import com.idltd.hydramob.repository.client_actions.MenuClientActionsRepository;
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
@RequestMapping("/client_actions")
public class ClientActionsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClientActionsRepository menuClientActionsRepository;
    private DetailClientActionsRepository detailClientActionsRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private Action_Type_ListRepository action_Type_ListRepository;
    private Action_Status_ListRepository action_Status_ListRepository;
    private Action_Result_ListRepository action_Result_ListRepository;

    public ClientActionsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClientActionsRepository menuClientActionsRepository,
            DetailClientActionsRepository detailClientActionsRepository,
            Contragent_ListRepository contragent_ListRepository,
            Action_Type_ListRepository action_Type_ListRepository,
            Action_Status_ListRepository action_Status_ListRepository,
            Action_Result_ListRepository action_Result_ListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuClientActionsRepository = menuClientActionsRepository;
        this.detailClientActionsRepository = detailClientActionsRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.action_Type_ListRepository = action_Type_ListRepository;
        this.action_Status_ListRepository = action_Status_ListRepository;
        this.action_Result_ListRepository = action_Result_ListRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "act_id", required = false) Long act_id,
                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,

                              @RequestParam(name = "cnt_name", required = false) String cnt_name,
                              @RequestParam(name = "cnt_user_access", required = false) Long cnt_user_access,

                              @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                              @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                              @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
                              @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                              @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

                              @RequestParam(name = "client_actions_list_table_order_column", required = false) Long client_actions_list_table_order_column,
                              @RequestParam(name = "client_actions_list_table_order_type", required = false) String client_actions_list_table_order_type,
                              @RequestParam(name = "client_actions_list_table_search", required = false) String client_actions_list_table_search,
                              @RequestParam(name = "client_actions_list_table_pagelen", required = false) Long client_actions_list_table_pagelen,
                              @RequestParam(name = "client_actions_list_table_page", required = false) Long client_actions_list_table_page,

                              @RequestParam(name = "client_actions_filter_id", required = false) Long client_actions_filter_id,
                              @RequestParam(name = "client_actions_filter_start_date", required = false) String client_actions_filter_start_date,
                              @RequestParam(name = "client_actions_filter_end_date", required = false) String client_actions_filter_end_date
    ){
        model.addObject("act_id", act_id);
        model.addObject("cnt_id", cnt_id);

        model.addObject("cnt_name", cnt_name);
        model.addObject("cnt_user_access", cnt_user_access);

        model.addObject("client_actions_list_table_order_column", client_actions_list_table_order_column);
        model.addObject("client_actions_list_table_order_type", client_actions_list_table_order_type);
        model.addObject("client_actions_list_table_search", client_actions_list_table_search);
        model.addObject("client_actions_list_table_pagelen", client_actions_list_table_pagelen);
        model.addObject("client_actions_list_table_page", client_actions_list_table_page);

        model.addObject("clients_list_table_order_column", clients_list_table_order_column);
        model.addObject("clients_list_table_order_type", clients_list_table_order_type);
        model.addObject("clients_list_table_search", clients_list_table_search);
        model.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        model.addObject("clients_list_table_page", clients_list_table_page);

        model.addObject("client_actions_filter_id", client_actions_filter_id);
        model.addObject("client_actions_filter_start_date", client_actions_filter_start_date);
        model.addObject("client_actions_filter_end_date", client_actions_filter_end_date);

        model.setViewName("client_actions/cover");
        return model;
    }

    @GetMapping("/open_client_actions")
    public ModelAndView index(
            ModelAndView model,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cnt_user_access", required = false) Long cnt_user_access,

            @RequestParam(name = "acton_search", required = false) String acton_search
    ) {
        model.addObject("cnt_id", cnt_id);
        model.addObject("cnt_name", cnt_name);
        model.addObject("cnt_user_access", cnt_user_access);

        model.addObject("clients_list_table_order_column", 0);
        model.addObject("clients_list_table_order_type", "asc");
        model.addObject("clients_list_table_search", acton_search);
        model.addObject("clients_list_table_pagelen", 25);
        model.addObject("clients_list_table_page", 1);

        model.setViewName("client_actions/cover");
        return model;
    }

    @PostMapping("/get_menu")
    public JSONDatatable gettable(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "client_actions_filter_id", required = false, defaultValue = "0") Long client_actions_filter_id,
            @RequestParam(name = "client_actions_filter_start_date", required = false) String client_actions_filter_start_date,
            @RequestParam(name = "client_actions_filter_end_date", required = false) String client_actions_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuClientActionsRepository.queryMenuClientActionsByID(
                user_List.get(0).id,user_Role_List.get(0).role_id, cnt_id, client_actions_filter_id, client_actions_filter_start_date, client_actions_filter_end_date
        ));

        return result;
    }

    @GetMapping("/get_users")
    public JSONDatatable get_users(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);

            result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
        }
        return result;
    }

    @GetMapping("/get_act_type")
    public JSONDatatable get_act_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(action_Type_ListRepository.queryGetAll());
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
                result.setData(contragent_ListRepository.queryCntByID(cnt_id));
            }
        }
        return result;
    }

    @GetMapping("/get_act_status")
    public JSONDatatable get_act_status(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(action_Status_ListRepository.queryEditStatus());
        }
        return result;
    }

    @GetMapping("/get_act_result")
    public JSONDatatable get_act_result(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode != null) {
            result.setData(action_Result_ListRepository.queryActionResultEditStatus());
        }
        return result;
    }

    @GetMapping("/get_act_detail")
    public JSONDatatable get_act_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="act_id", required = false, defaultValue = "0") Long act_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailClientActionsRepository.queryMenuActionsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, act_id
            ));
        }
        return result;
    }

    @PostMapping("/add_action")
    public ModelAndView add_action(
            @RequestParam(name = "cnt_id", required = false, defaultValue = "0") Long cnt_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "act_plane_date", required = false) String act_plane_date,
            @RequestParam(name = "act_end_date", required = false) String act_end_date,
            @RequestParam(name = "act_type_id", required = false) Long act_type_id,
            @RequestParam(name = "act_status_id", required = false) Long act_status_id,
            @RequestParam(name = "act_result_id", required = false, defaultValue = "0") Long act_result_id,
            @RequestParam(name = "act_description", required = false) String act_description,

            @RequestParam(name = "act_act_type_id", required = false, defaultValue = "1") Long act_act_type_id,
            @RequestParam(name = "cold_cnt_name", required = false, defaultValue = "") String cold_cnt_name,
            @RequestParam(name = "cold_contact", required = false, defaultValue = "") String cold_contact,
            @RequestParam(name = "cold_phone", required = false, defaultValue = "") String cold_phone
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddActQuery = entityManager
                    .createStoredProcedureQuery("PKG_ACTION.pr_AddActionRole")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_id)
                    .setParameter(4, cnt_id)
                    .setParameter(5, act_plane_date)
                    .setParameter(6, act_end_date)
                    .setParameter(7, act_type_id)
                    .setParameter(8, act_description)
                    .setParameter(9, act_status_id)
                    .setParameter(10, act_result_id)
                    .setParameter(11, new Long(1))
                    .setParameter(12, act_act_type_id)
                    .setParameter(13, cold_cnt_name)
                    .setParameter(14, cold_contact)
                    .setParameter(15, cold_phone);
            AddActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_action")
    public ModelAndView edit_action(
            @RequestParam(name = "act_id") Long act_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "act_plane_date", required = false) String act_plane_date,
            @RequestParam(name = "act_end_date", required = false) String act_end_date,
            @RequestParam(name = "act_type_id", required = false) Long act_type_id,
            @RequestParam(name = "act_status_id", required = false) Long act_status_id,
            @RequestParam(name = "act_result_id", required = false, defaultValue = "0") Long act_result_id,
            @RequestParam(name = "act_description", required = false) String act_description,

            @RequestParam(name = "act_act_type_id", required = false, defaultValue = "1") Long act_act_type_id,
            @RequestParam(name = "cold_cnt_name", required = false, defaultValue = "") String cold_cnt_name,
            @RequestParam(name = "cold_contact", required = false, defaultValue = "") String cold_contact,
            @RequestParam(name = "cold_phone", required = false, defaultValue = "") String cold_phone
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditActQuery = entityManager
                    .createStoredProcedureQuery("PKG_ACTION.pr_EditActionRole")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, act_id)
                    .setParameter(4, act_plane_date)
                    .setParameter(5, act_end_date)
                    .setParameter(6, act_type_id)
                    .setParameter(7, act_result_id)
                    .setParameter(8, act_description)
                    .setParameter(9, act_status_id)
                    .setParameter(10, act_act_type_id)
                    .setParameter(11, cold_cnt_name)
                    .setParameter(12, cold_contact)
                    .setParameter(13, cold_phone);
            EditActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_action")
    public ModelAndView del_action(
            @RequestParam(name = "act_id") Long act_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_ACTION.pr_DelActionRole")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, act_id);
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/action_clone")
    public ModelAndView action_clone(
            @RequestParam(name = "act_id") Long act_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery ActionCloneQuery = entityManager
                .createStoredProcedureQuery("PKG_ACTION.pr_CloneActionRole")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, act_id);
        ActionCloneQuery.execute();
        return null;
    }
}
