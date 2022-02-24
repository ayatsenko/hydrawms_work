package com.idltd.hydramob.controller.actions;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.actions.DetailActionsRepository;
import com.idltd.hydramob.repository.actions.MenuActionsRepository;
import com.idltd.hydramob.repository.list.ActionPlanTypeRepository;
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
@RequestMapping("/actions")
public class ActionsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuActionsRepository menuActionsRepository;
    private DetailActionsRepository detailActionsRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private Action_Type_ListRepository action_Type_ListRepository;
    private Action_Status_ListRepository action_Status_ListRepository;
    private Action_Result_ListRepository action_Result_ListRepository;
    private User_Report_ListRepository user_Report_ListRepository;
    private Client_Report_ListRepository client_Report_ListRepository;
    private ActionPlanTypeRepository actionPlanTypeRepository;

    public ActionsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuActionsRepository menuActionsRepository,
            DetailActionsRepository detailActionsRepository,
            Contragent_ListRepository contragent_ListRepository,
            Action_Type_ListRepository action_Type_ListRepository,
            Action_Status_ListRepository action_Status_ListRepository,
            Action_Result_ListRepository action_Result_ListRepository,
            User_Report_ListRepository user_Report_ListRepository,
            Client_Report_ListRepository client_Report_ListRepository,
            ActionPlanTypeRepository actionPlanTypeRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuActionsRepository = menuActionsRepository;
        this.detailActionsRepository = detailActionsRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.action_Type_ListRepository = action_Type_ListRepository;
        this.action_Status_ListRepository = action_Status_ListRepository;
        this.action_Result_ListRepository = action_Result_ListRepository;
        this.user_Report_ListRepository = user_Report_ListRepository;
        this.client_Report_ListRepository = client_Report_ListRepository;
        this.actionPlanTypeRepository = actionPlanTypeRepository;
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
                              @RequestParam(name = "table_page", required = false) Long table_page,

                              @RequestParam(name = "action_filter_id", required = false) Long action_filter_id,
                              @RequestParam(name = "action_filter_start_date", required = false) String action_filter_start_date,
                              @RequestParam(name = "action_filter_end_date", required = false) String action_filter_end_date,

                              @RequestParam(name = "action_filter_type_id", required = false) Long action_filter_type_id,
                              @RequestParam(name = "action_filter_action_id", required = false) Long action_filter_action_id,
                              @RequestParam(name = "action_filter_status_id", required = false) Long action_filter_status_id,
                              @RequestParam(name = "action_filter_result_id", required = false) Long action_filter_result_id,
                              @RequestParam(name = "action_filter_client_id", required = false) Long action_filter_client_id,
                              @RequestParam(name = "action_filter_user_id", required = false) Long action_filter_user_id
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

        model.addObject("action_filter_id", action_filter_id);
        model.addObject("action_filter_start_date", action_filter_start_date);
        model.addObject("action_filter_end_date", action_filter_end_date);

        if(action_filter_client_id != null) {
            model.addObject("action_filter_user_id", user_List.get(0).id);
        }
        else{
            model.addObject("action_filter_user_id", action_filter_action_id);
        }
        user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
        model.addObject("action_filter_user_list", user_Report_List);

        if(action_filter_user_id != null) {
            model.addObject("action_filter_user_id", user_List.get(0).id);
        }
        else{
            model.addObject("action_filter_user_id", action_filter_user_id);
        }
        client_Report_List = client_Report_ListRepository.queryGetClientByChiefID(user_List.get(0).id);
        model.addObject("action_filter_cnt_list", client_Report_List);

        model.addObject("action_filter_type_id", action_filter_type_id);
        action_Type_List = action_Type_ListRepository.queryGetAll();
        model.addObject("action_filter_type_list", action_Type_List);

        model.addObject("action_filter_status_id", action_filter_status_id);
        action_Status_List = action_Status_ListRepository.queryEditStatus();
        model.addObject("action_filter_status_list", action_Status_List);

        model.addObject("action_filter_result_id", action_filter_result_id);
        action_Result_List = action_Result_ListRepository.queryActionResultEditStatus();
        model.addObject("action_filter_result_list", action_Result_List);

        model.addObject("act_plan_type_id", action_filter_result_id);
        actionPlanType = actionPlanTypeRepository.queryActionPlanTypeByID();
        model.addObject("action_filter_plan_type_list", actionPlanType);

        model.setViewName("actions/cover");
        return model;
    }

    @PostMapping("/get_menu")
    public JSONDatatable gettable(
            @RequestParam(name = "action_filter_id", required = false, defaultValue = "0") Long action_filter_id,

            @RequestParam(name = "action_filter_start_date", required = false) String action_filter_start_date,
            @RequestParam(name = "action_filter_end_date", required = false) String action_filter_end_date,

            @RequestParam(name = "action_filter_type_id", required = false, defaultValue = "-2") Long action_filter_type_id,
            @RequestParam(name = "action_filter_action_id", required = false, defaultValue = "-2") Long action_filter_action_id,
            @RequestParam(name = "action_filter_status_id", required = false, defaultValue = "-2") Long action_filter_status_id,
            @RequestParam(name = "action_filter_result_id", required = false, defaultValue = "-2") Long action_filter_result_id,
            @RequestParam(name = "action_filter_client_id", required = false, defaultValue = "-2") Long action_filter_client_id,
            @RequestParam(name = "action_filter_user_id", required = false, defaultValue = "-2") Long action_filter_user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuActionsRepository.queryMenuActionsByID(
                user_List.get(0).id,
                user_Role_List.get(0).role_id,
                action_filter_id,
                action_filter_start_date,
                action_filter_end_date,
                action_filter_type_id,
                action_filter_action_id,
                action_filter_status_id,
                action_filter_result_id,
                action_filter_client_id,
                action_filter_user_id
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

            result.setData(user_ListRepository.queryUserByID(
                    user_List.get(0).id
            ));
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

        if(mode != null && mode > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailActionsRepository.queryDetailActionsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, act_id
            ));
        }
        else if(mode != null && mode == 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailActionsRepository.queryDetailNewActionsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id
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
