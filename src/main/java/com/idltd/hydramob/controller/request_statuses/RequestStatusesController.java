package com.idltd.hydramob.controller.request_statuses;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.request_statuses.RequestStatus;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.request_statuses.DetailRequestStatusesRepository;
import com.idltd.hydramob.repository.request_statuses.MenuRequestStatusesRepository;
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
@RequestMapping("/request_statuses")
public class RequestStatusesController {

    private MenuRequestStatusesRepository menuRequestStatusesRepository;
    private DetailRequestStatusesRepository detailRequestStatusesRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    public RequestStatusesController(
            MenuRequestStatusesRepository menuRequestStatusesRepository,
            User_ListRepository user_ListRepository,
            DetailRequestStatusesRepository detailRequestStatusesRepository,
            User_Role_ListRepository user_Role_ListRepository
    ) {
        this.menuRequestStatusesRepository = menuRequestStatusesRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailRequestStatusesRepository = detailRequestStatusesRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "req_status_id", required = false) Long req_status_id,
                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page

    ){
        model.addObject("req_status_id", req_status_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("request_statuses/cover");
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

        result.setData(menuRequestStatusesRepository.queryMenuRequestStatusesByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="req_status_id") Long req_status_id,
                                   @RequestParam(value="request_statuses_table_order_column") Long order_column,
                                   @RequestParam(value="request_statuses_table_order_type") String order_type,
                                   @RequestParam(value="request_statuses_table_search") String table_search,
                                   @RequestParam(value="request_statuses_table_pagelen") Long pagelen,
                                   @RequestParam(value="request_statuses_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<RequestStatus> requestStatusDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){

        }
        else{
            requestStatusDetail = detailRequestStatusesRepository.queryDetailRequestStatusesByID(user_List.get(0).id,user_Role_List.get(0).role_id, req_status_id);

            mav.addObject("req_status_id", requestStatusDetail.get(0).req_status_id);
            mav.addObject("req_status_name", requestStatusDetail.get(0).req_status_name);
            mav.addObject("req_status_sname", requestStatusDetail.get(0).req_status_sname);
            mav.addObject("req_status_description", requestStatusDetail.get(0).req_status_description);
            mav.addObject("req_status_colour", requestStatusDetail.get(0).req_status_colour);
            mav.addObject("req_status_order", requestStatusDetail.get(0).req_status_order);
        }

        mav.addObject("request_statuses_table_order_column", order_column);
        mav.addObject("request_statuses_table_order_type", order_type);
        mav.addObject("request_statuses_table_search", table_search);
        mav.addObject("request_statuses_table_pagelen", pagelen);
        mav.addObject("request_statuses_table_page", page);

        mav.setViewName("/request_statuses/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="req_status_id") Long req_status_id,
            @RequestParam(value="req_status_name", required = false) String req_status_name,
            @RequestParam(value="req_status_sname", required = false) String req_status_sname,
            @RequestParam(value="req_status_description", required = false) String req_status_description,
            @RequestParam(value="req_status_colour", required = false) String req_status_colour,
            @RequestParam(value="req_status_order") Long req_status_order,

            @RequestParam(value="request_statuses_table_order_column", required = false) Long order_column,
            @RequestParam(value="request_statuses_table_order_type", required = false) String order_type,
            @RequestParam(value="request_statuses_table_search", required = false) String table_search,
            @RequestParam(value="request_statuses_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="request_statuses_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            switch (mode.intValue()) {
                case 1:
                    StoredProcedureQuery EditRequestStatusQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditRequestStatusType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, req_status_id)
                            .setParameter(2, req_status_name)
                            .setParameter(3, req_status_sname)
                            .setParameter(4, req_status_description)
                            .setParameter(5, req_status_colour);
                    EditRequestStatusQuery.execute();
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

        mav.addObject("req_status_id", req_status_id);
        mav.addObject("req_status_name", req_status_name);
        mav.addObject("req_status_sname", req_status_sname);
        mav.addObject("req_status_description", req_status_description);
        mav.addObject("req_status_colour", req_status_colour);
        mav.addObject("req_status_order", req_status_order);

        mav.addObject("request_statuses_table_order_column", order_column);
        mav.addObject("request_statuses_table_order_type", order_type);
        mav.addObject("request_statuses_table_search", table_search);
        mav.addObject("request_statuses_table_pagelen", pagelen);
        mav.addObject("request_statuses_table_page", page);

        mav.setViewName("/request_statuses/detail");
        return mav;
    }
}
