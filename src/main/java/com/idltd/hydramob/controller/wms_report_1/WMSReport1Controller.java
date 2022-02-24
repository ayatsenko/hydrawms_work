package com.idltd.hydramob.controller.wms_report_1;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_report_1.MenuWMSReport1Repository;
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
@RequestMapping("/wms_report_1")
public class WMSReport1Controller {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuWMSReport1Repository menuWMSReport1Repository;

    public WMSReport1Controller(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuWMSReport1Repository menuWMSReport1Repository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuWMSReport1Repository = menuWMSReport1Repository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "row_id", required = false) Long row_id,

                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page,

                              @RequestParam(name = "wms_report_1_main_filter_id", required = false, defaultValue = "1") Long wms_report_1_main_filter_id,
                              @RequestParam(name = "wms_report_1_main_filter_start_date", required = false) String wms_report_1_main_filter_start_date,
                              @RequestParam(name = "wms_report_1_main_filter_end_date", required = false) String wms_report_1_main_filter_end_date
    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.addObject("row_id", row_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.addObject("wms_report_1_main_filter_id", wms_report_1_main_filter_id);
        model.addObject("wms_report_1_main_filter_start_date", wms_report_1_main_filter_start_date);
        model.addObject("wms_report_1_main_filter_end_date", wms_report_1_main_filter_end_date);

        model.setViewName("wms_report_1/cover");
        return model;
    }

    @PostMapping("/get_menu")
    public JSONDatatable gettable(
            @RequestParam(name = "wms_report_1_main_filter_id", required = false, defaultValue = "0") Long wms_report_1_main_filter_id,
            @RequestParam(name = "wms_report_1_main_filter_start_date", required = false) String wms_report_1_main_filter_start_date,
            @RequestParam(name = "wms_report_1_main_filter_end_date", required = false) String wms_report_1_main_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if (wms_report_1_main_filter_id != null && wms_report_1_main_filter_start_date != null && wms_report_1_main_filter_end_date != null &&
                wms_report_1_main_filter_id >= 0
        ) {
            result.setData(menuWMSReport1Repository.queryWMSReport1ByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    wms_report_1_main_filter_id,
                    wms_report_1_main_filter_start_date,
                    wms_report_1_main_filter_end_date
            ));
        }
        return result;
    }
}
