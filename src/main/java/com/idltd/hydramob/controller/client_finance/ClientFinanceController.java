package com.idltd.hydramob.controller.client_finance;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.client_finance.MenuClientFinanceListRepository;
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
@RequestMapping("/client_finance")
public class ClientFinanceController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClientFinanceListRepository menuClientFinanceListRepository;

    public ClientFinanceController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClientFinanceListRepository menuClientFinanceListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuClientFinanceListRepository = menuClientFinanceListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "fin_id", required = false) Long fin_id,
                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,

                              @RequestParam(name = "cnt_name", required = false) String cnt_name,
                              @RequestParam(name = "cnt_user_access", required = false) Long cnt_user_access,

                              @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                              @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                              @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
                              @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                              @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

                              @RequestParam(name = "client_finance_list_table_order_column", required = false) Long client_finance_list_table_order_column,
                              @RequestParam(name = "client_finance_list_table_order_type", required = false) String client_finance_list_table_order_type,
                              @RequestParam(name = "client_finance_list_table_search", required = false) String client_finance_list_table_search,
                              @RequestParam(name = "client_finance_list_table_pagelen", required = false) Long client_finance_list_table_pagelen,
                              @RequestParam(name = "client_finance_list_table_page", required = false) Long client_finance_list_table_page,

                              @RequestParam(name = "client_finance_filter_id", required = false) Long client_finance_filter_id,
                              @RequestParam(name = "client_finance_filter_start_date", required = false) String client_finance_filter_start_date,
                              @RequestParam(name = "client_finance_filter_end_date", required = false) String client_finance_filter_end_date
    ){
        model.addObject("fin_id", fin_id);
        model.addObject("cnt_id", cnt_id);

        model.addObject("cnt_name", cnt_name);
        model.addObject("cnt_user_access", cnt_user_access);

        model.addObject("client_finance_list_table_order_column", client_finance_list_table_order_column);
        model.addObject("client_finance_list_table_order_type", client_finance_list_table_order_type);
        model.addObject("client_finance_list_table_search", client_finance_list_table_search);
        model.addObject("client_finance_list_table_pagelen", client_finance_list_table_pagelen);
        model.addObject("client_finance_list_table_page", client_finance_list_table_page);

        model.addObject("clients_list_table_order_column", clients_list_table_order_column);
        model.addObject("clients_list_table_order_type", clients_list_table_order_type);
        model.addObject("clients_list_table_search", clients_list_table_search);
        model.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        model.addObject("clients_list_table_page", clients_list_table_page);

        model.addObject("client_finance_filter_id", client_finance_filter_id);
        model.addObject("client_finance_filter_start_date", client_finance_filter_start_date);
        model.addObject("client_finance_filter_end_date", client_finance_filter_end_date);

        model.setViewName("client_finance/cover");
        return model;
    }

    @PostMapping("/get_menu")
    public JSONDatatable gettable(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,

            @RequestParam(name = "client_finance_filter_id", required = false, defaultValue = "0") Long client_finance_filter_id,
            @RequestParam(name = "client_finance_filter_start_date", required = false) String client_finance_filter_start_date,
            @RequestParam(name = "client_finance_filter_end_date", required = false) String client_finance_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(cnt_id > 0 && cnt_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientFinanceListRepository.queryClientFinanceMenuByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, client_finance_filter_id, client_finance_filter_start_date, client_finance_filter_end_date
            ));
        }
        return result;
    }
}
