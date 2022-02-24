package com.idltd.hydramob.controller.action_types;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.action_types.ActionType;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.action_types.DetailActionTypesRepository;
import com.idltd.hydramob.repository.action_types.MenuActionTypesRepository;
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
@RequestMapping("/action_types")
public class ActionTypeController {

    private MenuActionTypesRepository menuActionTypesRepository;
    private DetailActionTypesRepository detailActionTypesRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    public ActionTypeController(
            MenuActionTypesRepository menuActionTypesRepository,
            User_ListRepository user_ListRepository,
            DetailActionTypesRepository detailActionTypesRepository,
            User_Role_ListRepository user_Role_ListRepository
    ) {
        this.menuActionTypesRepository = menuActionTypesRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailActionTypesRepository = detailActionTypesRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "act_type_id", required = false) Long act_type_id,
                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page

    ){
        model.addObject("act_type_id", act_type_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("action_types/cover");
        return model;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuActionTypesRepository.queryMenuActionTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="act_type_id") Long act_type_id,
                                   @RequestParam(value="action_types_table_order_column") Long order_column,
                                   @RequestParam(value="action_types_table_order_type") String order_type,
                                   @RequestParam(value="action_types_table_search") String table_search,
                                   @RequestParam(value="action_types_table_pagelen") Long pagelen,
                                   @RequestParam(value="action_types_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<ActionType> actionTypeDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){

        }
        else{
            actionTypeDetail = detailActionTypesRepository.queryDetailActionTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id, act_type_id);

            mav.addObject("act_type_id", actionTypeDetail.get(0).act_type_id);
            mav.addObject("act_type_name", actionTypeDetail.get(0).act_type_name);
            mav.addObject("act_type_sname", actionTypeDetail.get(0).act_type_sname);
            mav.addObject("act_type_description", actionTypeDetail.get(0).act_type_description);
            mav.addObject("act_type_colour", actionTypeDetail.get(0).act_type_colour);
            mav.addObject("act_type_short", actionTypeDetail.get(0).act_type_short);
        }

        mav.addObject("action_types_table_order_column", order_column);
        mav.addObject("action_types_table_order_type", order_type);
        mav.addObject("action_types_table_search", table_search);
        mav.addObject("action_types_table_pagelen", pagelen);
        mav.addObject("action_types_table_page", page);

        mav.setViewName("/action_types/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="act_type_id") Long act_type_id,
            @RequestParam(value="act_type_name", required = false) String act_type_name,
            @RequestParam(value="act_type_sname", required = false) String act_type_sname,
            @RequestParam(value="act_type_description", required = false) String act_type_description,
            @RequestParam(value="act_type_colour", required = false) String act_type_colour,
            @RequestParam(value="act_type_short", required = false) String act_type_short,

            @RequestParam(value="action_types_table_order_column", required = false) Long order_column,
            @RequestParam(value="action_types_table_order_type", required = false) String order_type,
            @RequestParam(value="action_types_table_search", required = false) String table_search,
            @RequestParam(value="action_types_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="action_types_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddActionTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_AddActionType")
                            .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, act_type_sname)
                            .setParameter(2, act_type_name)
                            .setParameter(3, act_type_description)
                            .setParameter(4, act_type_colour)
                            .setParameter(5, act_type_short);
                    AddActionTypeQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditActionTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditActionType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .setParameter(1, act_type_id)
                            .setParameter(2, act_type_sname)
                            .setParameter(3, act_type_name)
                            .setParameter(4, act_type_description)
                            .setParameter(5, act_type_colour)
                            .setParameter(6, act_type_short);
                    EditActionTypeQuery.execute();
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

        mav.addObject("act_type_id", act_type_id);
        mav.addObject("act_type_name", act_type_name);
        mav.addObject("act_type_sname", act_type_sname);
        mav.addObject("act_type_description", act_type_description);
        mav.addObject("act_type_colour", act_type_colour);
        mav.addObject("act_type_short", act_type_short);

        mav.addObject("action_types_table_order_column", order_column);
        mav.addObject("action_types_table_order_type", order_type);
        mav.addObject("action_types_table_search", table_search);
        mav.addObject("action_types_table_pagelen", pagelen);
        mav.addObject("action_types_table_page", page);

        mav.setViewName("/action_types/detail");
        return mav;
    }
}
