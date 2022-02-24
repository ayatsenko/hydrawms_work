package com.idltd.hydramob.controller.tms_cost_types;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.TMS_Costs_Source_List;
import com.idltd.hydramob.entity.tms_cost_types.DetailTMSCostType;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.list.TMS_Costs_Source_ListRepository;
import com.idltd.hydramob.repository.tms_cost_types.DetailTMSCostTypesRepository;
import com.idltd.hydramob.repository.tms_cost_types.MenuTMSCostTypesRepository;
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
@RequestMapping("/tms_cost_types")
public class TMSCostTypeController {

    private MenuTMSCostTypesRepository menuTMSCostTypesRepository;
    private DetailTMSCostTypesRepository detailTMSCostTypesRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private TMS_Costs_Source_ListRepository tms_Costs_Source_ListRepository;

    public TMSCostTypeController(
            MenuTMSCostTypesRepository menuTMSCostTypesRepository,
            User_ListRepository user_ListRepository,
            DetailTMSCostTypesRepository detailTMSCostTypesRepository,
            User_Role_ListRepository user_Role_ListRepository,
            TMS_Costs_Source_ListRepository tms_Costs_Source_ListRepository
    ) {
        this.menuTMSCostTypesRepository = menuTMSCostTypesRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailTMSCostTypesRepository = detailTMSCostTypesRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.tms_Costs_Source_ListRepository = tms_Costs_Source_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clmc_type_id", required = false) Long clmc_type_id,

                              @RequestParam(name = "tms_cost_types_table_order_column", required = false) Long tms_cost_types_table_order_column,
                              @RequestParam(name = "tms_cost_types_table_order_type", required = false) String tms_cost_types_table_order_type,
                              @RequestParam(name = "tms_cost_types_table_search", required = false) String tms_cost_types_table_search,
                              @RequestParam(name = "tms_cost_types_table_pagelen", required = false) Long tms_cost_types_table_pagelen,
                              @RequestParam(name = "tms_cost_types_table_page", required = false) Long tms_cost_types_table_page

    ){
        model.addObject("clmc_type_id", clmc_type_id);

        model.addObject("tms_cost_types_table_order_column", tms_cost_types_table_order_column);
        model.addObject("tms_cost_types_table_order_type", tms_cost_types_table_order_type);
        model.addObject("tms_cost_types_table_search", tms_cost_types_table_search);
        model.addObject("tms_cost_types_table_pagelen", tms_cost_types_table_pagelen);
        model.addObject("tms_cost_types_table_page", tms_cost_types_table_page);

        model.setViewName("tms_cost_types/cover");
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

        result.setData(menuTMSCostTypesRepository.queryMenuTMSCostTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="clmc_type_id") Long clmc_type_id,

                                   @RequestParam(value="tms_cost_types_table_order_column") Long order_column,
                                   @RequestParam(value="tms_cost_types_table_order_type") String order_type,
                                   @RequestParam(value="tms_cost_types_table_search") String table_search,
                                   @RequestParam(value="tms_cost_types_table_pagelen") Long pagelen,
                                   @RequestParam(value="tms_cost_types_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<DetailTMSCostType> detailTMSCostType;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Costs_Source_List> tms_Costs_Source_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("clmc_type_colour", "#ffffff");

            tms_Costs_Source_List = tms_Costs_Source_ListRepository.queryTMSCostsSourceListAll();
            mav.addObject("source_list", tms_Costs_Source_List);
        }
        else{
            detailTMSCostType = detailTMSCostTypesRepository.queryDetailTMSCostTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id, clmc_type_id);

            mav.addObject("clmc_type_id", detailTMSCostType.get(0).clmc_type_id);
            mav.addObject("clmc_type_name", detailTMSCostType.get(0).clmc_type_name);
            mav.addObject("clmc_type_sname", detailTMSCostType.get(0).clmc_type_sname);
            mav.addObject("clmc_type_description", detailTMSCostType.get(0).clmc_type_description);
            mav.addObject("clmc_type_colour", detailTMSCostType.get(0).clmc_type_colour);

            mav.addObject("clmc_source_id", detailTMSCostType.get(0).clmc_source_id);
            tms_Costs_Source_List = tms_Costs_Source_ListRepository.queryTMSCostsSourceListAll();
            mav.addObject("source_list", tms_Costs_Source_List);
        }

        mav.addObject("tms_cost_types_table_order_column", order_column);
        mav.addObject("tms_cost_types_table_order_type", order_type);
        mav.addObject("tms_cost_types_table_search", table_search);
        mav.addObject("tms_cost_types_table_pagelen", pagelen);
        mav.addObject("tms_cost_types_table_page", page);

        mav.setViewName("/tms_cost_types/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clmc_type_id") Long clmc_type_id,
            @RequestParam(value="clmc_type_name", required = false) String clmc_type_name,
            @RequestParam(value="clmc_type_sname", required = false) String clmc_type_sname,
            @RequestParam(value="clmc_type_description", required = false) String clmc_type_description,
            @RequestParam(value="clmc_type_colour", required = false) String clmc_type_colour,

            @RequestParam(value="clmc_source_id", required = false) Long clmc_source_id,

            @RequestParam(value="tms_cost_types_table_order_column", required = false) Long order_column,
            @RequestParam(value="tms_cost_types_table_order_type", required = false) String order_type,
            @RequestParam(value="tms_cost_types_table_search", required = false) String table_search,
            @RequestParam(value="tms_cost_types_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="tms_cost_types_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Costs_Source_List> tms_Costs_Source_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddTMSCostTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSCostType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmc_type_sname)
                            .setParameter(4, clmc_type_name)
                            .setParameter(5, clmc_type_description)
                            .setParameter(6, clmc_type_colour)
                            .setParameter(7, clmc_source_id)
                            ;
                    AddTMSCostTypeQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTMSCostTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSCostType")
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
                            .setParameter(3, clmc_type_id)
                            .setParameter(4, clmc_type_sname)
                            .setParameter(5, clmc_type_name)
                            .setParameter(6, clmc_type_description)
                            .setParameter(7, clmc_type_colour)
                            .setParameter(8, clmc_source_id)
                            ;
                    EditTMSCostTypeQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelTMSCostTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSCostType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmc_type_id);
                    DelTMSCostTypeQuery.execute();
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

        mav.addObject("clmc_type_id", clmc_type_id);
        mav.addObject("clmc_type_name", clmc_type_name);
        mav.addObject("clmc_type_sname", clmc_type_sname);
        mav.addObject("clmc_type_description", clmc_type_description);
        mav.addObject("clmc_type_colour", clmc_type_colour);

        mav.addObject("clmc_source_id", clmc_source_id);
        tms_Costs_Source_List = tms_Costs_Source_ListRepository.queryTMSCostsSourceListAll();
        mav.addObject("source_list", tms_Costs_Source_List);

        mav.addObject("tms_cost_types_table_order_column", order_column);
        mav.addObject("tms_cost_types_table_order_type", order_type);
        mav.addObject("tms_cost_types_table_search", table_search);
        mav.addObject("tms_cost_types_table_pagelen", pagelen);
        mav.addObject("tms_cost_types_table_page", page);

        mav.setViewName("/tms_cost_types/detail");
        return mav;
    }
}
