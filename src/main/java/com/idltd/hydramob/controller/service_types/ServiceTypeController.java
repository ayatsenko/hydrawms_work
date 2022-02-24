package com.idltd.hydramob.controller.service_types;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.service_types.DetailServiceTypeSystems;
import com.idltd.hydramob.entity.service_types.ServiceType;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.service_types.DetailServiceTypeRepository;
import com.idltd.hydramob.repository.service_types.DetailServiceTypeSystemsRepository;
import com.idltd.hydramob.repository.service_types.MenuServiceTypeRepository;
import com.idltd.hydramob.repository.service_types.MenuServiceTypeSystemsRepository;
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
@RequestMapping("/service_types")
public class ServiceTypeController {

    private MenuServiceTypeRepository menuServiceTypeRepository;
    private DetailServiceTypeRepository detailServiceTypeRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuServiceTypeSystemsRepository menuServiceTypeSystemsRepository;
    private DetailServiceTypeSystemsRepository detailServiceTypeSystemsRepository;

    public ServiceTypeController(
            MenuServiceTypeRepository menuServiceTypeRepository,
            User_ListRepository user_ListRepository,
            DetailServiceTypeRepository detailServiceTypeRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuServiceTypeSystemsRepository menuServiceTypeSystemsRepository,
            DetailServiceTypeSystemsRepository detailServiceTypeSystemsRepository
    ) {
        this.menuServiceTypeRepository = menuServiceTypeRepository;
        this.user_ListRepository = user_ListRepository;
        this.detailServiceTypeRepository = detailServiceTypeRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuServiceTypeSystemsRepository = menuServiceTypeSystemsRepository;
        this.detailServiceTypeSystemsRepository = detailServiceTypeSystemsRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "ser_id", required = false) Long ser_id,
                              @RequestParam(name = "sersl_id", required = false) Long sersl_id,

                              @RequestParam(name = "service_types_list_table_order_column", required = false) Long service_types_list_table_order_column,
                              @RequestParam(name = "service_types_list_table_order_type", required = false) String service_types_list_table_order_type,
                              @RequestParam(name = "service_types_list_table_search", required = false) String service_types_list_table_search,
                              @RequestParam(name = "service_types_list_table_pagelen", required = false) Long service_types_list_table_pagelen,
                              @RequestParam(name = "service_types_list_table_page", required = false) Long service_types_list_table_page,

                              @RequestParam(name = "service_types_systems_table_order_column", required = false) Long service_types_systems_table_order_column,
                              @RequestParam(name = "service_types_systems_table_order_type", required = false) String service_types_systems_table_order_type,
                              @RequestParam(name = "service_types_systems_table_search", required = false) String service_types_systems_table_search,
                              @RequestParam(name = "service_types_systems_table_pagelen", required = false) Long service_types_systems_table_pagelen,
                              @RequestParam(name = "service_types_systems_table_page", required = false) Long service_types_systems_table_page
    ){
        model.addObject("ser_id", ser_id);

        model.addObject("service_types_list_table_order_column", service_types_list_table_order_column);
        model.addObject("service_types_list_table_order_type", service_types_list_table_order_type);
        model.addObject("service_types_list_table_search", service_types_list_table_search);
        model.addObject("service_types_list_table_pagelen", service_types_list_table_pagelen);
        model.addObject("service_types_list_table_page", service_types_list_table_page);

        model.addObject("sersl_id", sersl_id);

        model.addObject("service_types_systems_table_order_column", service_types_systems_table_order_column);
        model.addObject("service_types_systems_table_order_type", service_types_systems_table_order_type);
        model.addObject("service_types_systems_table_search", service_types_systems_table_search);
        model.addObject("service_types_systems_table_pagelen", service_types_systems_table_pagelen);
        model.addObject("service_types_systems_table_page", service_types_systems_table_page);

        model.setViewName("service_types/cover");
        return model;
    }

    @PostMapping("/list_gettable")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuServiceTypeRepository.queryMenuServiceTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/list_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="ser_id") Long ser_id,

                                   @RequestParam(value="service_types_list_table_order_column") Long order_column,
                                   @RequestParam(value="service_types_list_table_order_type") String order_type,
                                   @RequestParam(value="service_types_list_table_search") String table_search,
                                   @RequestParam(value="service_types_list_table_pagelen") Long pagelen,
                                   @RequestParam(value="service_types_list_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<ServiceType> serviceTypeDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){

        }
        else{
            serviceTypeDetail = detailServiceTypeRepository.queryDetailServiceTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id, ser_id);

            mav.addObject("ser_id", serviceTypeDetail.get(0).ser_id);
            mav.addObject("ser_name", serviceTypeDetail.get(0).ser_name);
            mav.addObject("ser_sname", serviceTypeDetail.get(0).ser_sname);
            mav.addObject("ser_description", serviceTypeDetail.get(0).ser_description);
        }

        mav.addObject("service_types_list_table_order_column", order_column);
        mav.addObject("service_types_list_table_order_type", order_type);
        mav.addObject("service_types_list_table_search", table_search);
        mav.addObject("service_types_list_table_pagelen", pagelen);
        mav.addObject("service_types_list_table_page", page);

        mav.setViewName("/service_types/list_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/list_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="ser_id") Long ser_id,
            @RequestParam(value="ser_name", required = false) String ser_name,
            @RequestParam(value="ser_sname", required = false) String ser_sname,
            @RequestParam(value="ser_description", required = false) String ser_description,

            @RequestParam(value="service_types_list_table_order_column", required = false) Long order_column,
            @RequestParam(value="service_types_list_table_order_type", required = false) String order_type,
            @RequestParam(value="service_types_list_table_search", required = false) String table_search,
            @RequestParam(value="service_types_list_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="service_types_list_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddSerTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_AddServiceType")
                            .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .setParameter(1, ser_sname)
                            .setParameter(2, ser_name)
                            .setParameter(3, ser_description);
                    AddSerTypeQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditSerTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditServiceType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .setParameter(1, ser_id)
                            .setParameter(2, ser_sname)
                            .setParameter(3, ser_name)
                            .setParameter(4, ser_description);
                    EditSerTypeQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelSerTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_DelServiceType")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .setParameter(1, ser_id);
                    DelSerTypeQuery.execute();
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

        mav.addObject("ser_id", ser_id);
        mav.addObject("ser_name", ser_name);
        mav.addObject("ser_sname", ser_sname);
        mav.addObject("ser_description", ser_description);

        mav.addObject("service_types_list_table_order_column", order_column);
        mav.addObject("service_types_list_table_order_type", order_type);
        mav.addObject("service_types_list_table_search", table_search);
        mav.addObject("service_types_list_table_pagelen", pagelen);
        mav.addObject("service_types_list_table_page", page);

        mav.setViewName("/service_types/list_detail");
        return mav;
    }

    @PostMapping("/systems_gettable")
    public JSONDatatable systems_gettable(
            @RequestParam(value="ser_id") Long ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;
        if(ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuServiceTypeSystemsRepository.queryMenuServiceTypeSystemByID(user_List.get(0).id, user_Role_List.get(0).role_id, ser_id));
        }
        return result;
    }

    @RequestMapping("/systems_detail")
    public ModelAndView system_detail(@RequestParam(value="mode") Long mode,
                                      @RequestParam(value="ser_id") Long ser_id,
                                      @RequestParam(value="ser_name") String ser_name,

                                      @RequestParam(value="sersl_id") Long sersl_id,

                                      @RequestParam(value="service_types_systems_table_order_column") Long service_types_systems_table_order_column,
                                      @RequestParam(value="service_types_systems_table_order_type") String service_types_systems_table_order_type,
                                      @RequestParam(value="service_types_systems_table_search") String service_types_systems_table_search,
                                      @RequestParam(value="service_types_systems_table_pagelen") Long service_types_systems_table_pagelen,
                                      @RequestParam(value="service_types_systems_table_page") Long service_types_systems_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<DetailServiceTypeSystems> serviceTypeSystemDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("ser_id", ser_id);
        mav.addObject("ser_name", ser_name);

        if(mode == 0){

        }
        else{
            serviceTypeSystemDetail = detailServiceTypeSystemsRepository.queryDetailServiceTypeSystemByID(user_List.get(0).id,user_Role_List.get(0).role_id, sersl_id);

            mav.addObject("sersl_id", serviceTypeSystemDetail.get(0).sersl_id);
            mav.addObject("sersl_name", serviceTypeSystemDetail.get(0).sersl_name);
        }

        mav.addObject("service_types_systems_table_order_column", service_types_systems_table_order_column);
        mav.addObject("service_types_systems_table_order_type", service_types_systems_table_order_type);
        mav.addObject("service_types_systems_table_search", service_types_systems_table_search);
        mav.addObject("service_types_systems_table_pagelen", service_types_systems_table_pagelen);
        mav.addObject("service_types_systems_table_page", service_types_systems_table_page);

        mav.setViewName("/service_types/systems_detail");
        return mav;
    }

    @PostMapping("/systems_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="ser_id") Long ser_id,
            @RequestParam(value="ser_name", required = false) String ser_name,

            @RequestParam(value="sersl_id", required = false) Long sersl_id,
            @RequestParam(value="sersl_name", required = false) String sersl_name,

            @RequestParam(value="service_types_system_table_order_column", required = false) Long service_types_system_table_order_column,
            @RequestParam(value="service_types_system_table_order_type", required = false) String service_types_system_table_order_type,
            @RequestParam(value="service_types_system_table_search", required = false) String service_types_system_table_search,
            @RequestParam(value="service_types_system_table_pagelen", required = false) Long service_types_system_table_pagelen,
            @RequestParam(value="service_types_system_table_page", required = false) Long service_types_system_table_page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddSerTypeSystemQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_AddCrmServiceSystemLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, ser_id)
                            .setParameter(4, sersl_name);
                    AddSerTypeSystemQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditSerTypeSystemQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditCrmServiceSystemLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, sersl_id)
                            .setParameter(4, sersl_name);
                    EditSerTypeSystemQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelSerTypeSystemQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_DelCrmServiceSystemLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, sersl_id);
                    DelSerTypeSystemQuery.execute();
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

        mav.addObject("ser_id", ser_id);
        mav.addObject("ser_name", ser_name);

        mav.addObject("sersl_id", sersl_id);
        mav.addObject("sersl_name", sersl_name);

        mav.addObject("service_types_system_table_order_column", service_types_system_table_order_column);
        mav.addObject("service_types_system_table_order_type", service_types_system_table_order_type);
        mav.addObject("service_types_system_table_search", service_types_system_table_search);
        mav.addObject("service_types_system_table_pagelen", service_types_system_table_pagelen);
        mav.addObject("service_types_system_table_page", service_types_system_table_page);

        mav.setViewName("/service_types/systems_detail");
        return mav;
    }
}
