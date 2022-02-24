package com.idltd.hydramob.controller.auto_tasks;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.auto_tasks.MenuAutoDKFilesRepository;
import com.idltd.hydramob.repository.auto_tasks.MenuAutoFinanceFilesRepository;
import com.idltd.hydramob.repository.auto_tasks.MenuAutoTasksRepository;
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
@RequestMapping("/auto_tasks")
public class AutoTasksController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuAutoTasksRepository menuAutoTasksRepository;
    private MenuAutoFinanceFilesRepository menuAutoFinanceFilesRepository;
    private MenuAutoDKFilesRepository menuAutoDKFilesRepository;

    public AutoTasksController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuAutoTasksRepository menuAutoTasksRepository,
            MenuAutoFinanceFilesRepository menuAutoFinanceFilesRepository,
            MenuAutoDKFilesRepository menuAutoDKFilesRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuAutoTasksRepository = menuAutoTasksRepository;
        this.menuAutoFinanceFilesRepository = menuAutoFinanceFilesRepository;
        this.menuAutoDKFilesRepository = menuAutoDKFilesRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "se_id", required = false) Long se_id,

                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page
    ){
        model.addObject("se_id", se_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("auto_tasks/cover");
        return model;
    }

    @PostMapping("/get_main")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuAutoTasksRepository.queryMenuAutoTasksAll(
                user_List.get(0).id,user_Role_List.get(0).role_id
        ));

        return result;
    }

    @PostMapping("/finance_file_main")
    public JSONDatatable finance_file_main(
            @RequestParam(name = "sfa_id", required = false) Long sfa_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(sfa_id != null) {

            List<User_Role_List> user_Role_List;
            List<User_List> user_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuAutoFinanceFilesRepository.queryMenuAutoFinanceFile(user_List.get(0).id, user_Role_List.get(0).role_id, sfa_id));
        }
        return result;
    }

    @PostMapping("/dk_file_main")
    public JSONDatatable dk_file_main(
            @RequestParam(name = "sfa_id", required = false) Long sfa_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(sfa_id != null) {
            List<User_Role_List> user_Role_List;
            List<User_List> user_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuAutoDKFilesRepository.queryMenuDKFileAll());
        }
        return result;
    }
}
