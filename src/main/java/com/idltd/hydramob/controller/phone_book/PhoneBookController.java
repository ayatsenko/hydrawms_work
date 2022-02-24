package com.idltd.hydramob.controller.phone_book;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.phone_book.MenuDetailPhoneBookRepository;
import com.idltd.hydramob.repository.phone_book.MenuPhoneBookRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/phone_book")
public class PhoneBookController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuPhoneBookRepository menuPhoneBookRepository;
    private MenuDetailPhoneBookRepository menuDetailPhoneBookRepository;

    public PhoneBookController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuPhoneBookRepository menuPhoneBookRepository,
            MenuDetailPhoneBookRepository menuDetailPhoneBookRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuPhoneBookRepository = menuPhoneBookRepository;
        this.menuDetailPhoneBookRepository = menuDetailPhoneBookRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "cc_id", required = false) Long cc_id,
                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page

    ){
        model.addObject("cc_id", cc_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("phone_book/cover");
        return model;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuPhoneBookRepository.queryPhoneBookByUserID(user_List.get(0).id));

        return result;
    }

    @GetMapping("/get_contact_detail")
    public JSONDatatable get_contact_detail(
            @RequestParam(name = "cc_id", required = false) Long cc_id,
            @RequestParam(name = "pb_type_id", required = false) Long pb_type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if( cc_id > 0 ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuDetailPhoneBookRepository.queryPhoneBookMenuDetailByUserID(user_List.get(0).id, cc_id, pb_type_id));
        }
        return result;
    }
}
