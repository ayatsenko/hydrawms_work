package com.idltd.hydramob.controller.users_kpi_ops;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.Users_KPI_Type_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.users_kpi_ops.MenuUserKPIOPSCalcListRepository;
import com.idltd.hydramob.repository.users_kpi_ops.MenuUserKPIOPSMainListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users_kpi_ops")
public class UsersKPIOPSController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuUserKPIOPSMainListRepository menuUserKPIOPSMainListRepository;
    private MenuUserKPIOPSCalcListRepository menuUserKPIOPSCalcListRepository;

    public UsersKPIOPSController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuUserKPIOPSMainListRepository menuUserKPIOPSMainListRepository,
            MenuUserKPIOPSCalcListRepository menuUserKPIOPSCalcListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuUserKPIOPSMainListRepository = menuUserKPIOPSMainListRepository;
        this.menuUserKPIOPSCalcListRepository = menuUserKPIOPSCalcListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "users_id", required = false) Long users_id,
                              @RequestParam(name = "users_kpi_year", required = false) Long users_kpi_year,

                              @RequestParam(name = "users_kpi_facts_list_table_order_column", required = false) Long users_kpi_facts_list_table_order_column,
                              @RequestParam(name = "users_kpi_facts_list_table_order_type", required = false) String users_kpi_facts_list_table_order_type,
                              @RequestParam(name = "users_kpi_facts_list_table_search", required = false) String users_kpi_facts_list_table_search,
                              @RequestParam(name = "users_kpi_facts_list_table_pagelen", required = false) Long users_kpi_facts_list_table_pagelen,
                              @RequestParam(name = "users_kpi_facts_list_table_page", required = false) Long users_kpi_facts_list_table_page
    ){
        List<Users_KPI_Type_List> users_KPI_Type_List;

        model.addObject("users_id", users_id);

        model.addObject("users_kpi_year", users_kpi_year);

        model.addObject("users_kpi_facts_list_table_order_column", users_kpi_facts_list_table_order_column);
        model.addObject("users_kpi_facts_list_table_order_type", users_kpi_facts_list_table_order_type);
        model.addObject("users_kpi_facts_list_table_search", users_kpi_facts_list_table_search);
        model.addObject("users_kpi_facts_list_table_pagelen", users_kpi_facts_list_table_pagelen);
        model.addObject("users_kpi_facts_list_table_page", users_kpi_facts_list_table_page);

        model.setViewName("users_kpi_ops/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "users_kpi_ops_year", required = false) Long users_kpi_ops_year
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(users_kpi_ops_year != null) {
            result.setData(menuUserKPIOPSMainListRepository.queryUserKPIOPSMainListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_ops_year));
        }
        return result;
    }

    @PostMapping("/get_calc_table")
    public JSONDatatable get_calc_table(
            @RequestParam(name = "users_kpi_user_id", required = false) Long users_kpi_user_id,
            @RequestParam(name = "users_kpi_ops_year", required = false) Long users_kpi_ops_year,
            @RequestParam(name = "users_kpi_ops_month", required = false) Long users_kpi_ops_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(users_kpi_user_id != null && users_kpi_ops_year != null && users_kpi_ops_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIOPSCalcListRepository.queryUserKPICalcMainListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, users_kpi_user_id, users_kpi_ops_year, users_kpi_ops_month
            ));
        }
        return result;
    }
}
