package com.idltd.hydramob.controller.request_types;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.request_types.RequestType;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.request_types.DetailRequestTypesRepository;
import com.idltd.hydramob.repository.request_types.MenuRequestTypesRepository;
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
@RequestMapping("/request_types")
public class RequestTypeController {

    private MenuRequestTypesRepository menuRequestTypesRepository;
    private DetailRequestTypesRepository detailRequestTypesRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    public RequestTypeController(
            MenuRequestTypesRepository menuRequestTypesRepository,
            User_ListRepository user_ListRepository,
            DetailRequestTypesRepository detailRequestTypesRepository,
            User_Role_ListRepository user_Role_ListRepository
    ) {
        this.menuRequestTypesRepository = menuRequestTypesRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailRequestTypesRepository = detailRequestTypesRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "req_type_id", required = false) Long req_type_id,
                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page

    ){
        model.addObject("req_type_id", req_type_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("request_types/cover");
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

        result.setData(menuRequestTypesRepository.queryMenuRequestTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="req_type_id") Long req_type_id,
                                   @RequestParam(value="request_types_table_order_column") Long order_column,
                                   @RequestParam(value="request_types_table_order_type") String order_type,
                                   @RequestParam(value="request_types_table_search") String table_search,
                                   @RequestParam(value="request_types_table_pagelen") Long pagelen,
                                   @RequestParam(value="request_types_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<RequestType> actionTypeDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){

        }
        else{
            actionTypeDetail = detailRequestTypesRepository.queryDetailRequestTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_type_id);

            mav.addObject("req_type_id", actionTypeDetail.get(0).req_type_id);
            mav.addObject("req_type_name", actionTypeDetail.get(0).req_type_name);
            mav.addObject("req_type_sname", actionTypeDetail.get(0).req_type_sname);
            mav.addObject("req_type_description", actionTypeDetail.get(0).req_type_description);
            mav.addObject("req_type_colour", actionTypeDetail.get(0).req_type_colour);
        }

        mav.addObject("request_types_table_order_column", order_column);
        mav.addObject("request_types_table_order_type", order_type);
        mav.addObject("request_types_table_search", table_search);
        mav.addObject("request_types_table_pagelen", pagelen);
        mav.addObject("request_types_table_page", page);

        mav.setViewName("/request_types/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="req_type_id") Long req_type_id,
            @RequestParam(value="req_type_name", required = false) String req_type_name,
            @RequestParam(value="req_type_sname", required = false) String req_type_sname,
            @RequestParam(value="req_type_description", required = false) String req_type_description,
            @RequestParam(value="req_type_colour", required = false) String req_type_colour,

            @RequestParam(value="request_types_table_order_column", required = false) Long order_column,
            @RequestParam(value="request_types_table_order_type", required = false) String order_type,
            @RequestParam(value="request_types_table_search", required = false) String table_search,
            @RequestParam(value="request_types_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="request_types_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            switch (mode.intValue()) {
                case 1:
                    StoredProcedureQuery EditReqTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditRequestType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, req_type_id)
                            .setParameter(2, req_type_name)
                            .setParameter(3, req_type_sname)
                            .setParameter(4, req_type_description)
                            .setParameter(5, req_type_colour);
                    EditReqTypeQuery.execute();
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

        mav.addObject("req_type_id", req_type_id);
        mav.addObject("req_type_name", req_type_name);
        mav.addObject("req_type_sname", req_type_sname);
        mav.addObject("req_type_description", req_type_description);
        mav.addObject("req_type_colour", req_type_colour);

        mav.addObject("request_types_table_order_column", order_column);
        mav.addObject("request_types_table_order_type", order_type);
        mav.addObject("request_types_table_search", table_search);
        mav.addObject("request_types_table_pagelen", pagelen);
        mav.addObject("request_types_table_page", page);

        mav.setViewName("/request_types/detail");
        return mav;
    }
}
