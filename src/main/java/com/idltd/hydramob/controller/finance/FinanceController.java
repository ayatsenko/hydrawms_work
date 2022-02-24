package com.idltd.hydramob.controller.finance;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.finance.MenuFinanceListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuFinanceListRepository menuFinanceListRepository;

    public FinanceController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuFinanceListRepository menuFinanceListRepository

    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuFinanceListRepository = menuFinanceListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "fin_id", required = false) Long fin_id,

                              @RequestParam(name = "finance_list_table_order_column", required = false) Long finance_list_table_order_column,
                              @RequestParam(name = "finance_list_table_order_type", required = false) String finance_list_table_order_type,
                              @RequestParam(name = "finance_list_table_search", required = false) String finance_list_table_search,
                              @RequestParam(name = "finance_list_table_pagelen", required = false) Long finance_list_table_pagelen,
                              @RequestParam(name = "finance_list_table_page", required = false) Long finance_list_table_page,

                              @RequestParam(name = "finance_filter_id", required = false, defaultValue = "1") Long finance_filter_id,
                              @RequestParam(name = "finance_filter_start_date", required = false) String finance_filter_start_date,
                              @RequestParam(name = "finance_filter_end_date", required = false) String finance_filter_end_date
    ){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = dateFormat.format(date);

        model.addObject("fin_id", fin_id);

        model.addObject("finance_list_table_order_column", finance_list_table_order_column);
        model.addObject("finance_list_table_order_type", finance_list_table_order_type);
        model.addObject("finance_list_table_search", finance_list_table_search);
        model.addObject("finance_list_table_pagelen", finance_list_table_pagelen);
        model.addObject("finance_list_table_page", finance_list_table_page);

        model.addObject("finance_filter_id", finance_filter_id);
        if(finance_filter_start_date == null) {
            model.addObject("finance_filter_start_date", strDate);
        }else{
            model.addObject("finance_filter_start_date", finance_filter_start_date);
        }

        if(finance_filter_end_date == null) {
            model.addObject("finance_filter_end_date", strDate);
        }else{
            model.addObject("finance_filter_end_date", finance_filter_end_date);
        }

        model.setViewName("finance/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "finance_filter_id", required = false, defaultValue = "0") Long finance_filter_id,
            @RequestParam(name = "finance_filter_start_date", required = false) String finance_filter_start_date,
            @RequestParam(name = "finance_filter_end_date", required = false) String finance_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuFinanceListRepository.queryFinanceMenuByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id,finance_filter_id,finance_filter_start_date,finance_filter_end_date
        ));
        return result;
    }
}
