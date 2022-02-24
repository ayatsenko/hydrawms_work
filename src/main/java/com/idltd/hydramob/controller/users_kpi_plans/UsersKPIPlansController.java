package com.idltd.hydramob.controller.users_kpi_plans;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.Users_KPI_Type_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.list.Users_KPI_Type_ListRepository;
import com.idltd.hydramob.repository.users_kpi_plans.MenuUserKPIPlansListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/users_kpi_plans")
public class UsersKPIPlansController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private Users_KPI_Type_ListRepository users_KPI_Type_ListRepository;

    private MenuUserKPIPlansListRepository menuUserKPIPlansListRepository;
    public UsersKPIPlansController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            Users_KPI_Type_ListRepository users_KPI_Type_ListRepository,

            MenuUserKPIPlansListRepository menuUserKPIPlansListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.users_KPI_Type_ListRepository = users_KPI_Type_ListRepository;

        this.menuUserKPIPlansListRepository = menuUserKPIPlansListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "users_id", required = false) Long users_id,
                              @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,
                              @RequestParam(name = "user_kpi_type_id", required = false) Long user_kpi_type_id,

                              @RequestParam(name = "users_kpi_plans_list_table_order_column", required = false) Long users_kpi_plans_list_table_order_column,
                              @RequestParam(name = "users_kpi_plans_list_table_order_type", required = false) String users_kpi_plans_list_table_order_type,
                              @RequestParam(name = "users_kpi_plans_list_table_search", required = false) String users_kpi_plans_list_table_search,
                              @RequestParam(name = "users_kpi_plans_list_table_pagelen", required = false) Long users_kpi_plans_list_table_pagelen,
                              @RequestParam(name = "users_kpi_plans_list_table_page", required = false) Long users_kpi_plans_list_table_page

    ){
        List<Users_KPI_Type_List> users_KPI_Type_List;

        model.addObject("users_id", users_id);

        model.addObject("users_kpi_year", users_kpi_year);

        model.addObject("user_kpi_type_id", user_kpi_type_id);
        users_KPI_Type_List = users_KPI_Type_ListRepository.queryUsersKPITypeAll();
        model.addObject("user_kpi_type_list", users_KPI_Type_List);

        model.addObject("users_kpi_plans_list_table_order_column", users_kpi_plans_list_table_order_column);
        model.addObject("users_kpi_plans_list_table_order_type", users_kpi_plans_list_table_order_type);
        model.addObject("users_kpi_plans_list_table_search", users_kpi_plans_list_table_search);
        model.addObject("users_kpi_plans_list_table_pagelen", users_kpi_plans_list_table_pagelen);
        model.addObject("users_kpi_plans_list_table_page", users_kpi_plans_list_table_page);

        model.setViewName("users_kpi_plans/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "users_kpi_plans_year", required = false) Long users_kpi_plans_year,
            @RequestParam(name = "user_kpi_type_id", required = false) Long user_kpi_type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(users_kpi_plans_year != null) {
            result.setData(menuUserKPIPlansListRepository.queryUserKPIPlansListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_plans_year, user_kpi_type_id));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/users_kpi_plans_edit")
    public JSONDatatable users_kpi_plans_edit(
            @RequestParam(name = "user_kpi_year", required = false) Long user_kpi_year,
            @RequestParam(name = "user_kpi_type_id", required = false) Long user_kpi_type_id,
            @RequestParam(name = "user_kpi_id", required = false) Long user_kpi_id,
            @RequestParam(name = "month_1", required = false) String month_1,
            @RequestParam(name = "month_2", required = false) String month_2,
            @RequestParam(name = "month_3", required = false) String month_3,
            @RequestParam(name = "month_4", required = false) String month_4,
            @RequestParam(name = "month_5", required = false) String month_5,
            @RequestParam(name = "month_6", required = false) String month_6,
            @RequestParam(name = "month_7", required = false) String month_7,
            @RequestParam(name = "month_8", required = false) String month_8,
            @RequestParam(name = "month_9", required = false) String month_9,
            @RequestParam(name = "month_10", required = false) String month_10,
            @RequestParam(name = "month_11", required = false) String month_11,
            @RequestParam(name = "month_12", required = false) String month_12
    ) {
        if(user_kpi_year != null && user_kpi_year > 0 && user_kpi_type_id != null && user_kpi_type_id > 0 && user_kpi_id != null && user_kpi_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditUserKPIPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_EditUSER_KPI_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_year)
                    .setParameter(4, user_kpi_type_id)
                    .setParameter(5, user_kpi_id)
                    .setParameter(6, month_1)
                    .setParameter(7, month_2)
                    .setParameter(8, month_3)
                    .setParameter(9, month_4)
                    .setParameter(10, month_5)
                    .setParameter(11, month_6)
                    .setParameter(12, month_7)
                    .setParameter(13, month_8)
                    .setParameter(14, month_9)
                    .setParameter(15, month_10)
                    .setParameter(16, month_11)
                    .setParameter(17, month_12)
                    ;
            EditUserKPIPlanQuery.execute();
        }
        return null;
    }

    @PostMapping("/users_kpi_plans_del")
    public JSONDatatable users_kpi_plans_del(
            @RequestParam(name = "user_kpi_year", required = false) Long user_kpi_year,
            @RequestParam(name = "user_kpi_type_id", required = false) Long user_kpi_type_id,
            @RequestParam(name = "user_kpi_id", required = false) Long user_kpi_id
    ) {
        if(user_kpi_year != null && user_kpi_year > 0 && user_kpi_type_id != null && user_kpi_type_id > 0 && user_kpi_id != null && user_kpi_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditUserKPIPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelUSER_KPI_PLAN")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_year)
                    .setParameter(4, user_kpi_type_id)
                    .setParameter(5, user_kpi_id)
                    ;
            EditUserKPIPlanQuery.execute();
        }
        return null;
    }

}
