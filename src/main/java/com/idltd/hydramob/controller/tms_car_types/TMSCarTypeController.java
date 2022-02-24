package com.idltd.hydramob.controller.tms_car_types;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.tms_car_types.DetailTMSCarType;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.tms_car_types.DetailTMSCarTypesRepository;
import com.idltd.hydramob.repository.tms_car_types.MenuTMSCarTypesRepository;
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
@RequestMapping("/tms_car_types")
public class TMSCarTypeController {

    private MenuTMSCarTypesRepository menuTMSCarTypesRepository;
    private DetailTMSCarTypesRepository detailTMSCarTypesRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    public TMSCarTypeController(
            MenuTMSCarTypesRepository menuTMSCarTypesRepository,
            User_ListRepository user_ListRepository,
            DetailTMSCarTypesRepository detailTMSCarTypesRepository,
            User_Role_ListRepository user_Role_ListRepository
    ) {
        this.menuTMSCarTypesRepository = menuTMSCarTypesRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailTMSCarTypesRepository = detailTMSCarTypesRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "cntc_type_id", required = false) Long cntc_type_id,

                              @RequestParam(name = "tms_car_types_table_order_column", required = false) Long tms_car_types_table_order_column,
                              @RequestParam(name = "tms_car_types_table_order_type", required = false) String tms_car_types_table_order_type,
                              @RequestParam(name = "tms_car_types_table_search", required = false) String tms_car_types_table_search,
                              @RequestParam(name = "tms_car_types_table_pagelen", required = false) Long tms_car_types_table_pagelen,
                              @RequestParam(name = "tms_car_types_table_page", required = false) Long tms_car_types_table_page

    ){
        model.addObject("cntc_type_id", cntc_type_id);

        model.addObject("tms_car_types_table_order_column", tms_car_types_table_order_column);
        model.addObject("tms_car_types_table_order_type", tms_car_types_table_order_type);
        model.addObject("tms_car_types_table_search", tms_car_types_table_search);
        model.addObject("tms_car_types_table_pagelen", tms_car_types_table_pagelen);
        model.addObject("tms_car_types_table_page", tms_car_types_table_page);

        model.setViewName("tms_car_types/cover");
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

        result.setData(menuTMSCarTypesRepository.queryMenuTMSCarTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="cntc_type_id") Long cntc_type_id,

                                   @RequestParam(value="tms_car_types_table_order_column") Long order_column,
                                   @RequestParam(value="tms_car_types_table_order_type") String order_type,
                                   @RequestParam(value="tms_car_types_table_search") String table_search,
                                   @RequestParam(value="tms_car_types_table_pagelen") Long pagelen,
                                   @RequestParam(value="tms_car_types_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<DetailTMSCarType> detailTMSCarType;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cntc_type_colour", "#ffffff");
        }
        else{
            detailTMSCarType = detailTMSCarTypesRepository.queryDetailTMSCarTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id, cntc_type_id);

            mav.addObject("cntc_type_id", detailTMSCarType.get(0).cntc_type_id);
            mav.addObject("cntc_type_name", detailTMSCarType.get(0).cntc_type_name);
            mav.addObject("cntc_type_sname", detailTMSCarType.get(0).cntc_type_sname);
            mav.addObject("cntc_type_description", detailTMSCarType.get(0).cntc_type_description);
            mav.addObject("cntc_type_colour", detailTMSCarType.get(0).cntc_type_colour);
        }

        mav.addObject("tms_car_types_table_order_column", order_column);
        mav.addObject("tms_car_types_table_order_type", order_type);
        mav.addObject("tms_car_types_table_search", table_search);
        mav.addObject("tms_car_types_table_pagelen", pagelen);
        mav.addObject("tms_car_types_table_page", page);

        mav.setViewName("/tms_car_types/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cntc_type_id") Long cntc_type_id,
            @RequestParam(value="cntc_type_name", required = false) String cntc_type_name,
            @RequestParam(value="cntc_type_sname", required = false) String cntc_type_sname,
            @RequestParam(value="cntc_type_description", required = false) String cntc_type_description,
            @RequestParam(value="cntc_type_colour", required = false) String cntc_type_colour,

            @RequestParam(value="tms_car_types_table_order_column", required = false) Long order_column,
            @RequestParam(value="tms_car_types_table_order_type", required = false) String order_type,
            @RequestParam(value="tms_car_types_table_search", required = false) String table_search,
            @RequestParam(value="tms_car_types_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="tms_car_types_table_page", required = false) Long page
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
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSCarType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cntc_type_sname)
                            .setParameter(4, cntc_type_name)
                            .setParameter(5, cntc_type_description)
                            .setParameter(6, cntc_type_colour);
                    AddTMSCarTypeQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSCarType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cntc_type_id)
                            .setParameter(4, cntc_type_sname)
                            .setParameter(5, cntc_type_name)
                            .setParameter(6, cntc_type_description)
                            .setParameter(7, cntc_type_colour);
                    EditTMSCarTypeQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSCarType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cntc_type_id);
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

        mav.addObject("cntc_type_id", cntc_type_id);
        mav.addObject("cntc_type_name", cntc_type_name);
        mav.addObject("cntc_type_sname", cntc_type_sname);
        mav.addObject("cntc_type_description", cntc_type_description);
        mav.addObject("cntc_type_colour", cntc_type_colour);

        mav.addObject("tms_car_types_table_order_column", order_column);
        mav.addObject("tms_car_types_table_order_type", order_type);
        mav.addObject("tms_car_types_table_search", table_search);
        mav.addObject("tms_car_types_table_pagelen", pagelen);
        mav.addObject("tms_car_types_table_page", page);

        mav.setViewName("/tms_car_types/detail");
        return mav;
    }
}
