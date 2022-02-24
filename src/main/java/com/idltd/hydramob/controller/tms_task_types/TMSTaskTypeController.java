package com.idltd.hydramob.controller.tms_task_types;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.tms_task_types.DetailTMSTaskType;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.tms_task_types.DetailTMSTaskTypesRepository;
import com.idltd.hydramob.repository.tms_task_types.MenuTMSTaskTypesRepository;
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
@RequestMapping("/tms_task_types")
public class TMSTaskTypeController {

    private MenuTMSTaskTypesRepository menuTMSTaskTypesRepository;
    private DetailTMSTaskTypesRepository detailTMSTaskTypesRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    public TMSTaskTypeController(
            MenuTMSTaskTypesRepository menuTMSTaskTypesRepository,
            User_ListRepository user_ListRepository,
            DetailTMSTaskTypesRepository detailTMSTaskTypesRepository,
            User_Role_ListRepository user_Role_ListRepository
    ) {
        this.menuTMSTaskTypesRepository = menuTMSTaskTypesRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailTMSTaskTypesRepository = detailTMSTaskTypesRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clm_task_id", required = false) Long clm_task_id,

                              @RequestParam(name = "tms_task_types_table_order_column", required = false) Long tms_task_types_table_order_column,
                              @RequestParam(name = "tms_task_types_table_order_type", required = false) String tms_task_types_table_order_type,
                              @RequestParam(name = "tms_task_types_table_search", required = false) String tms_task_types_table_search,
                              @RequestParam(name = "tms_task_types_table_pagelen", required = false) Long tms_task_types_table_pagelen,
                              @RequestParam(name = "tms_task_types_table_page", required = false) Long tms_task_types_table_page

    ){
        model.addObject("clm_task_id", clm_task_id);

        model.addObject("tms_task_types_table_order_column", tms_task_types_table_order_column);
        model.addObject("tms_task_types_table_order_type", tms_task_types_table_order_type);
        model.addObject("tms_task_types_table_search", tms_task_types_table_search);
        model.addObject("tms_task_types_table_pagelen", tms_task_types_table_pagelen);
        model.addObject("tms_task_types_table_page", tms_task_types_table_page);

        model.setViewName("tms_task_types/cover");
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

        result.setData(menuTMSTaskTypesRepository.queryMenuTMSTaskTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="clm_task_id") Long clm_task_id,

                                   @RequestParam(value="tms_task_types_table_order_column") Long order_column,
                                   @RequestParam(value="tms_task_types_table_order_type") String order_type,
                                   @RequestParam(value="tms_task_types_table_search") String table_search,
                                   @RequestParam(value="tms_task_types_table_pagelen") Long pagelen,
                                   @RequestParam(value="tms_task_types_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<DetailTMSTaskType> detailTMSTaskType;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("clm_task_colour", "#ffffff");

        }
        else{
            detailTMSTaskType = detailTMSTaskTypesRepository.queryDetailTMSTaskTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id, clm_task_id);

            mav.addObject("clm_task_id", detailTMSTaskType.get(0).clm_task_id);
            mav.addObject("clm_task_name", detailTMSTaskType.get(0).clm_task_name);
            mav.addObject("clm_task_sname", detailTMSTaskType.get(0).clm_task_sname);
            mav.addObject("clm_task_description", detailTMSTaskType.get(0).clm_task_description);
            mav.addObject("clm_task_colour", detailTMSTaskType.get(0).clm_task_colour);
            mav.addObject("clm_task_default", detailTMSTaskType.get(0).clm_task_default);
        }

        mav.addObject("tms_task_types_table_order_column", order_column);
        mav.addObject("tms_task_types_table_order_type", order_type);
        mav.addObject("tms_task_types_table_search", table_search);
        mav.addObject("tms_task_types_table_pagelen", pagelen);
        mav.addObject("tms_task_types_table_page", page);

        mav.setViewName("/tms_task_types/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_task_id") Long clm_task_id,
            @RequestParam(value="clm_task_name", required = false) String clm_task_name,
            @RequestParam(value="clm_task_sname", required = false) String clm_task_sname,
            @RequestParam(value="clm_task_description", required = false) String clm_task_description,
            @RequestParam(value="clm_task_colour", required = false) String clm_task_colour,
            @RequestParam(value="clm_task_default", required = false) Boolean clm_task_default,

            @RequestParam(value="tms_task_types_table_order_column", required = false) Long order_column,
            @RequestParam(value="tms_task_types_table_order_type", required = false) String order_type,
            @RequestParam(value="tms_task_types_table_search", required = false) String table_search,
            @RequestParam(value="tms_task_types_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="tms_task_types_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        Long ClmTaskDefault;
        if(clm_task_default == null){ ClmTaskDefault = new Long("0");}
        else { ClmTaskDefault = new Long("1");}

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddTMSTaskTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSTaskType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_task_sname)
                            .setParameter(4, clm_task_name)
                            .setParameter(5, clm_task_description)
                            .setParameter(6, clm_task_colour)
                            .setParameter(7, ClmTaskDefault)
                            ;
                    AddTMSTaskTypeQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTMSTaskTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSTaskType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_task_id)
                            .setParameter(4, clm_task_sname)
                            .setParameter(5, clm_task_name)
                            .setParameter(6, clm_task_description)
                            .setParameter(7, clm_task_colour)
                            .setParameter(8, ClmTaskDefault)
                            ;
                    EditTMSTaskTypeQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelTMSTaskTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSTaskType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_task_id);
                    DelTMSTaskTypeQuery.execute();
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

        mav.addObject("clm_task_id", clm_task_id);
        mav.addObject("clm_task_name", clm_task_name);
        mav.addObject("clm_task_sname", clm_task_sname);
        mav.addObject("clm_task_description", clm_task_description);
        mav.addObject("clm_task_colour", clm_task_colour);

        mav.addObject("tms_task_types_table_order_column", order_column);
        mav.addObject("tms_task_types_table_order_type", order_type);
        mav.addObject("tms_task_types_table_search", table_search);
        mav.addObject("tms_task_types_table_pagelen", pagelen);
        mav.addObject("tms_task_types_table_page", page);

        mav.setViewName("/tms_task_types/detail");
        return mav;
    }
}
