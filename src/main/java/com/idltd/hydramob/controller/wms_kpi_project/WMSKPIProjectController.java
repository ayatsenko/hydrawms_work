package com.idltd.hydramob.controller.wms_kpi_project;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_kpi_project.*;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/wms_kpi_project")
public class WMSKPIProjectController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuWMSKPIProjectDepartmentRepository menuWMSKPIProjectDepartmentRepository;

    private MenuWMSKPIProjectDepartmentProjectRepository menuWMSKPIProjectDepartmentProjectRepository;

    private MenuWMSKPIProjectDepartmentUsersRepository menuWMSKPIProjectDepartmentUsersRepository;

    private MenuWMSKPIProjectDepartmentCalculationRepository menuWMSKPIProjectDepartmentCalculationRepository;
    private MenuWMSKPIProjectDepartmentCalculationSecondRepository menuWMSKPIProjectDepartmentCalculationSecondRepository;

    private MenuWMSKPIProjectDepartmentDaysRepository menuWMSKPIProjectDepartmentDaysRepository;

    private MenuWMSKPIProjectDepartmentDaysFinanceRepository menuWMSKPIProjectDepartmentDaysFinanceRepository;

    private MenuWMSKPIProjectDepartmentDaysTextRepository menuWMSKPIProjectDepartmentDaysTextRepository;
    public WMSKPIProjectController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuWMSKPIProjectDepartmentRepository menuWMSKPIProjectDepartmentRepository,

            MenuWMSKPIProjectDepartmentProjectRepository menuWMSKPIProjectDepartmentProjectRepository,

            MenuWMSKPIProjectDepartmentUsersRepository menuWMSKPIProjectDepartmentUsersRepository,

            MenuWMSKPIProjectDepartmentCalculationRepository menuWMSKPIProjectDepartmentCalculationRepository,
            MenuWMSKPIProjectDepartmentCalculationSecondRepository menuWMSKPIProjectDepartmentCalculationSecondRepository,

            MenuWMSKPIProjectDepartmentDaysRepository menuWMSKPIProjectDepartmentDaysRepository,

            MenuWMSKPIProjectDepartmentDaysFinanceRepository menuWMSKPIProjectDepartmentDaysFinanceRepository,

            MenuWMSKPIProjectDepartmentDaysTextRepository menuWMSKPIProjectDepartmentDaysTextRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuWMSKPIProjectDepartmentRepository = menuWMSKPIProjectDepartmentRepository;

        this.menuWMSKPIProjectDepartmentProjectRepository = menuWMSKPIProjectDepartmentProjectRepository;

        this.menuWMSKPIProjectDepartmentUsersRepository = menuWMSKPIProjectDepartmentUsersRepository;

        this.menuWMSKPIProjectDepartmentCalculationRepository = menuWMSKPIProjectDepartmentCalculationRepository;
        this.menuWMSKPIProjectDepartmentCalculationSecondRepository = menuWMSKPIProjectDepartmentCalculationSecondRepository;

        this.menuWMSKPIProjectDepartmentDaysRepository = menuWMSKPIProjectDepartmentDaysRepository;

        this.menuWMSKPIProjectDepartmentDaysFinanceRepository = menuWMSKPIProjectDepartmentDaysFinanceRepository;

        this.menuWMSKPIProjectDepartmentDaysTextRepository = menuWMSKPIProjectDepartmentDaysTextRepository;
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

         model.setViewName("wms_kpi_project/cover");
        return model;
    }

    @PostMapping("/get_wms_kpi_project_department_menu")
    public JSONDatatable get_wms_kpi_project_department_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        if(kpi_year != null && kpi_year > 0) {
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentRepository.queryWMSKPIProjectDepartmentByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year
            ));
        }
        return result;
    }

    @PostMapping("/get_wms_kpi_project_department_project_menu")
    public JSONDatatable get_wms_kpi_project_department_project_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year,
            @RequestParam(name = "req_wms_dep_id", required = false) Long req_wms_dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        if(kpi_year != null && req_wms_dep_id != null && kpi_year > 0 && req_wms_dep_id > 0) {
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentProjectRepository.queryWMSKPIProjectDepartmentProjectByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year,
                    req_wms_dep_id
            ));
        }
        return result;
    }

    @PostMapping("/get_wms_kpi_project_department_users_menu")
    public JSONDatatable get_wms_kpi_project_department_users_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year,
            @RequestParam(name = "req_wms_dep_id", required = false) Long req_wms_dep_id,
            @RequestParam(name = "req_wms_project_id", required = false) Long req_wms_project_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        if(kpi_year != null && req_wms_dep_id != null && req_wms_project_id != null && kpi_year > 0 && req_wms_dep_id > 0 && req_wms_project_id > 0) {
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentUsersRepository.queryWMSKPIProjectDepartmentUsersByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year,
                    req_wms_dep_id,
                    req_wms_project_id
            ));
        }
        return result;
    }
/*Calculation First*/
    @PostMapping("/get_wms_kpi_project_department_calculation_first_menu")
    public JSONDatatable get_wms_kpi_project_department_calculation_first_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year,
            @RequestParam(name = "req_wms_dep_id", required = false) Long req_wms_dep_id,
            @RequestParam(name = "req_wms_project_id", required = false) Long req_wms_project_id,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            @RequestParam(name = "req_month_id", required = false) Long req_month_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(kpi_year != null && req_wms_dep_id != null && req_wms_project_id != null && req_month_id != null
                && kpi_year > 0 && req_wms_dep_id > 0 && req_wms_project_id > 0 && req_month_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentCalculationRepository.queryWMSKPIProjectDepartmentUsersDetailByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year,
                    req_wms_dep_id,
                    req_wms_project_id,
                    req_user_id,
                    req_month_id
            ));
        }
        return result;
    }
/*Calculation Second*/
    @PostMapping("/get_wms_kpi_project_department_calculation_second_menu")
    public JSONDatatable get_wms_kpi_project_department_calculation_second_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year,
            @RequestParam(name = "req_wms_dep_id", required = false) Long req_wms_dep_id,
            @RequestParam(name = "req_wms_project_id", required = false) Long req_wms_project_id,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            @RequestParam(name = "req_month_id", required = false) Long req_month_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(kpi_year != null && req_wms_dep_id != null && req_wms_project_id != null && req_month_id != null
                && kpi_year > 0 && req_wms_dep_id > 0 && req_wms_project_id > 0 && req_month_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentCalculationSecondRepository.queryWMSKPIProjectDepartmentCalculationDetailByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year,
                    req_wms_dep_id,
                    req_wms_project_id,
                    req_user_id,
                    req_month_id
            ));
        }
        return result;
    }

    @PostMapping("/get_wms_kpi_project_department_days_menu")
    public JSONDatatable get_wms_kpi_project_department_days_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year,
            @RequestParam(name = "req_wms_dep_id", required = false) Long req_wms_dep_id,
            @RequestParam(name = "req_wms_project_id", required = false) Long req_wms_project_id,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            @RequestParam(name = "req_month_id", required = false) Long req_month_id,
            @RequestParam(name = "req_wms_kpi_param_id", required = false) Long req_wms_kpi_param_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(kpi_year != null && req_wms_dep_id != null && req_wms_project_id != null && req_month_id != null && req_wms_kpi_param_id != null
                && kpi_year > 0 && req_wms_dep_id > 0 && req_wms_project_id > 0 && req_month_id > 0 && req_wms_kpi_param_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentDaysRepository.queryWMSKPIProjectDepartmentDaysByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year,
                    req_wms_dep_id,
                    req_wms_project_id,
                    req_user_id,
                    req_month_id,
                    req_wms_kpi_param_id
            ));
        }
        return result;
    }

    @PostMapping("/get_wms_kpi_project_department_days_finance_menu")
    public JSONDatatable get_wms_kpi_project_department_days_finance_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year,
            @RequestParam(name = "req_wms_dep_id", required = false) Long req_wms_dep_id,
            @RequestParam(name = "req_wms_project_id", required = false) Long req_wms_project_id,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            @RequestParam(name = "req_month_id", required = false) Long req_month_id,
            @RequestParam(name = "req_wms_kpi_param_id", required = false) Long req_wms_kpi_param_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(kpi_year != null && req_wms_dep_id != null && req_wms_project_id != null && req_month_id != null && req_wms_kpi_param_id != null
                && kpi_year > 0 && req_wms_dep_id > 0 && req_wms_project_id > 0 && req_month_id > 0 && req_wms_kpi_param_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentDaysFinanceRepository.queryWMSKPIProjectDepartmentDaysFinanceByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year,
                    req_wms_dep_id,
                    req_wms_project_id,
                    req_user_id,
                    req_month_id,
                    req_wms_kpi_param_id
            ));
        }
        return result;
    }

    @PostMapping("/get_wms_kpi_project_department_days_text_menu")
    public JSONDatatable get_wms_kpi_project_department_days_text_menu(
            @RequestParam(name = "kpi_year", required = false) Long kpi_year,
            @RequestParam(name = "req_wms_dep_id", required = false) Long req_wms_dep_id,
            @RequestParam(name = "req_wms_project_id", required = false) Long req_wms_project_id,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            @RequestParam(name = "req_month_id", required = false) Long req_month_id,
            @RequestParam(name = "req_wms_kpi_param_id", required = false) Long req_wms_kpi_param_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(kpi_year != null && req_wms_dep_id != null && req_wms_project_id != null && req_month_id != null && req_wms_kpi_param_id != null
                && kpi_year > 0 && req_wms_dep_id > 0 && req_wms_project_id > 0 && req_month_id > 0 && req_wms_kpi_param_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSKPIProjectDepartmentDaysTextRepository.queryWMSKPIProjectDepartmentDaysTextByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    kpi_year,
                    req_wms_dep_id,
                    req_wms_project_id,
                    req_user_id,
                    req_month_id,
                    req_wms_kpi_param_id
            ));
        }
        return result;
    }
}
