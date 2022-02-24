package com.idltd.hydramob.controller.tms_users;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.TMSCarsList;
import com.idltd.hydramob.entity.tms_users.DetailTMSUsersChat;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.list.TMSCarsListRepository;
import com.idltd.hydramob.repository.tms_users.DetailTMSUsersChatRepository;
import com.idltd.hydramob.repository.tms_users.MenuTMSUsersChatRepository;
import com.idltd.hydramob.repository.tms_users.MenuTMSUsersListRepository;
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
@RequestMapping("/tms_users")
public class TMSUsersController {

    private MenuTMSUsersListRepository menuTMSUsersListRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuTMSUsersChatRepository menuTMSUsersChatRepository;
    private TMSCarsListRepository tmsCarsListRepository;
    private DetailTMSUsersChatRepository detailTMSUsersChatRepository;

    public TMSUsersController(
            MenuTMSUsersListRepository menuTMSUsersListRepository,
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuTMSUsersChatRepository menuTMSUsersChatRepository,
            TMSCarsListRepository tmsCarsListRepository,
            DetailTMSUsersChatRepository detailTMSUsersChatRepository
    ) {
        this.menuTMSUsersListRepository = menuTMSUsersListRepository;
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuTMSUsersChatRepository = menuTMSUsersChatRepository;
        this.tmsCarsListRepository = tmsCarsListRepository;
        this.detailTMSUsersChatRepository = detailTMSUsersChatRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "user_id", required = false) Long user_id,

                              @RequestParam(name = "tms_users_list_table_order_column", required = false) Long tms_users_list_table_order_column,
                              @RequestParam(name = "tms_users_list_table_order_type", required = false) String tms_users_list_table_order_type,
                              @RequestParam(name = "tms_users_list_table_search", required = false) String tms_users_list_table_search,
                              @RequestParam(name = "tms_users_list_table_pagelen", required = false) Long tms_users_list_table_pagelen,
                              @RequestParam(name = "tms_users_list_table_page", required = false) Long tms_users_list_table_page,

                              @RequestParam(name = "tms_users_chat_table_order_column", required = false) Long tms_users_chat_table_order_column,
                              @RequestParam(name = "tms_users_chat_table_order_type", required = false) String tms_users_chat_table_order_type,
                              @RequestParam(name = "tms_users_chat_table_search", required = false) String tms_users_chat_table_search,
                              @RequestParam(name = "tms_users_chat_table_pagelen", required = false) Long tms_users_chat_table_pagelen,
                              @RequestParam(name = "tms_users_chat_table_page", required = false) Long tms_users_chat_table_page

    ){
        model.addObject("user_id", user_id);

        model.addObject("tms_users_list_table_order_column", tms_users_list_table_order_column);
        model.addObject("tms_users_list_table_order_type", tms_users_list_table_order_type);
        model.addObject("tms_users_list_table_search", tms_users_list_table_search);
        model.addObject("tms_users_list_table_pagelen", tms_users_list_table_pagelen);
        model.addObject("tms_users_list_table_page", tms_users_list_table_page);

        model.addObject("tms_users_chat_table_order_column", tms_users_chat_table_order_column);
        model.addObject("tms_users_chat_table_order_type", tms_users_chat_table_order_type);
        model.addObject("tms_users_chat_table_search", tms_users_chat_table_search);
        model.addObject("tms_users_chat_table_pagelen", tms_users_chat_table_pagelen);
        model.addObject("tms_users_chat_table_page", tms_users_chat_table_page);

        model.setViewName("tms_users/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuTMSUsersListRepository.queryMenuTMSUsersListByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @PostMapping("/get_chat_table")
    public JSONDatatable get_chat_table(
            @RequestParam(name = "req_user_id", required = false) Long req_user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;
        if(req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuTMSUsersChatRepository.queryMenuTMSUsersChatByID(user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id));
        }
        return result;
    }

    @RequestMapping("/chat_detail")
    public ModelAndView chat_detail(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="tms_chat_id", required = false) Long tms_chat_id,
                                   @RequestParam(value="req_user_id") Long req_user_id,
                                   @RequestParam(value="req_user_name") String req_user_name,

                                   @RequestParam(value="tms_users_chat_table_order_column", required = false) Long tms_users_chat_table_order_column,
                                   @RequestParam(value="tms_users_chat_table_order_type", required = false) String tms_users_chat_table_order_type,
                                   @RequestParam(value="tms_users_chat_table_search", required = false) String tms_users_chat_table_search,
                                   @RequestParam(value="tms_users_chat_table_pagelen", required = false) Long tms_users_chat_table_pagelen,
                                   @RequestParam(value="tms_users_chat_table_page", required = false) Long tms_users_chat_table_page,

                                   @RequestParam(name = "tms_users_list_table_order_column", required = false) Long tms_users_list_table_order_column,
                                   @RequestParam(name = "tms_users_list_table_order_type", required = false) String tms_users_list_table_order_type,
                                   @RequestParam(name = "tms_users_list_table_search", required = false) String tms_users_list_table_search,
                                   @RequestParam(name = "tms_users_list_table_pagelen", required = false) Long tms_users_list_table_pagelen,
                                   @RequestParam(name = "tms_users_list_table_page", required = false) Long tms_users_list_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMSCarsList> tmsCarsList;
        List<DetailTMSUsersChat> detailTMSUsersChat;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("req_user_id", req_user_id);
            mav.addObject("req_user_name", req_user_name);

            tmsCarsList = tmsCarsListRepository.queryTMSCarsListGetAll();
            mav.addObject("tms_car_list", tmsCarsList);
        }
        else{
            detailTMSUsersChat = detailTMSUsersChatRepository.queryDetailTMSUsersChatByID(user_List.get(0).id, user_Role_List.get(0).role_id, tms_chat_id);
            mav.addObject("tms_chat_id", detailTMSUsersChat.get(0).tms_chat_id);

            mav.addObject("req_user_id", detailTMSUsersChat.get(0).user_id);
            mav.addObject("req_user_name", detailTMSUsersChat.get(0).user_name);

            mav.addObject("tms_car_id", detailTMSUsersChat.get(0).tms_car_id);
            tmsCarsList = tmsCarsListRepository.queryTMSCarsListGetByID(detailTMSUsersChat.get(0).tms_car_id);
            mav.addObject("tms_car_list", tmsCarsList);

            mav.addObject("tms_chat_text", detailTMSUsersChat.get(0).tms_chat_text);
        }

        mav.addObject("tms_users_chat_table_order_column", tms_users_chat_table_order_column);
        mav.addObject("tms_users_chat_table_order_type", tms_users_chat_table_order_type);
        mav.addObject("tms_users_chat_table_search", tms_users_chat_table_search);
        mav.addObject("tms_users_chat_table_pagelen", tms_users_chat_table_pagelen);
        mav.addObject("tms_users_chat_table_page", tms_users_chat_table_page);

        mav.addObject("tms_users_list_table_order_column", tms_users_list_table_order_column);
        mav.addObject("tms_users_list_table_order_type", tms_users_list_table_order_type);
        mav.addObject("tms_users_list_table_search", tms_users_list_table_search);
        mav.addObject("tms_users_list_table_pagelen", tms_users_list_table_pagelen);
        mav.addObject("tms_users_list_table_page", tms_users_list_table_page);

        mav.setViewName("/tms_users/chat_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/chat_model")
    public ModelAndView chat_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="tms_chat_id", required = false) Long tms_chat_id,
            @RequestParam(value="tms_car_id") Long tms_car_id,
            @RequestParam(value="req_user_id") Long req_user_id,
            @RequestParam(value="tms_chat_text", required = false) String tms_chat_text,

            @RequestParam(value="tms_users_chat_table_order_column", required = false) Long tms_users_chat_table_order_column,
            @RequestParam(value="tms_users_chat_table_order_type", required = false) String tms_users_chat_table_order_type,
            @RequestParam(value="tms_users_chat_table_search", required = false) String tms_users_chat_table_search,
            @RequestParam(value="tms_users_chat_table_pagelen", required = false) Long tms_users_chat_table_pagelen,
            @RequestParam(value="tms_users_chat_table_page", required = false) Long tms_users_chat_table_page,

            @RequestParam(name = "tms_users_list_table_order_column", required = false) Long tms_users_list_table_order_column,
            @RequestParam(name = "tms_users_list_table_order_type", required = false) String tms_users_list_table_order_type,
            @RequestParam(name = "tms_users_list_table_search", required = false) String tms_users_list_table_search,
            @RequestParam(name = "tms_users_list_table_pagelen", required = false) Long tms_users_list_table_pagelen,
            @RequestParam(name = "tms_users_list_table_page", required = false) Long tms_users_list_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS_POLAND.pr_AddTmsCarChat")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, tms_car_id)
                            .setParameter(4, req_user_id)
                            .setParameter(5, tms_chat_text)
                            ;
                    AddTMSCarTypeQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS_POLAND.pr_EditTmsCarChat")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, tms_chat_id)
                            .setParameter(4, tms_car_id)
                            .setParameter(5, req_user_id)
                            .setParameter(6, tms_chat_text)
                            ;
                    EditTMSCarTypeQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS_POLAND.pr_DelTmsCarChat")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, tms_chat_id);
                    DelTMSCarTypeQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("req_user_id", req_user_id);
        mav.addObject("user_id", req_user_id);

        mav.addObject("tms_users_chat_table_order_column", tms_users_chat_table_order_column);
        mav.addObject("tms_users_chat_table_order_type", tms_users_chat_table_order_type);
        mav.addObject("tms_users_chat_table_search", tms_users_chat_table_search);
        mav.addObject("tms_users_chat_table_pagelen", tms_users_chat_table_pagelen);
        mav.addObject("tms_users_chat_table_page", tms_users_chat_table_page);

        mav.addObject("tms_users_list_table_order_column", tms_users_list_table_order_column);
        mav.addObject("tms_users_list_table_order_type", tms_users_list_table_order_type);
        mav.addObject("tms_users_list_table_search", tms_users_list_table_search);
        mav.addObject("tms_users_list_table_pagelen", tms_users_list_table_pagelen);
        mav.addObject("tms_users_list_table_page", tms_users_list_table_page);

        mav.setViewName("/tms_users/chat_detail");
        return mav;
    }
}
