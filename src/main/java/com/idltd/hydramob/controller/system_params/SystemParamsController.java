package com.idltd.hydramob.controller.system_params;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.system_params.MenuSystemParamsRepository;
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
@RequestMapping("/system_params")
public class SystemParamsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuSystemParamsRepository menuSystemParamsRepository;

    public SystemParamsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuSystemParamsRepository menuSystemParamsRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuSystemParamsRepository = menuSystemParamsRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "ser_id", required = false) Long ser_id,

                              @RequestParam(name = "system_params_table_order_column", required = false) Long system_params_table_order_column,
                              @RequestParam(name = "system_params_table_order_type", required = false) String system_params_table_order_type,
                              @RequestParam(name = "system_params_table_search", required = false) String system_params_table_search,
                              @RequestParam(name = "system_params_table_pagelen", required = false) Long system_params_table_pagelen,
                              @RequestParam(name = "system_params_table_page", required = false) Long system_params_table_page
    ){
        model.addObject("ser_id", ser_id);

        model.addObject("system_params_table_order_column", system_params_table_order_column);
        model.addObject("system_params_table_order_type", system_params_table_order_type);
        model.addObject("system_params_table_search", system_params_table_search);
        model.addObject("system_params_table_pagelen", system_params_table_pagelen);
        model.addObject("system_params_table_page", system_params_table_page);

        model.setViewName("system_params/cover");
        return model;
    }

    @PostMapping("/system_params_main")
    public JSONDatatable system_params_main(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuSystemParamsRepository.queryMenuSystemParamsByID(
                user_List.get(0).id,user_Role_List.get(0).role_id
        ));

        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/edit_system_params")
    public ModelAndView edit_system_params(
            @RequestParam(name = "crm_sys_par_id") Long crm_sys_par_id,
            @RequestParam(name = "crm_sys_par_client") String crm_sys_par_client
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_ADMIN.pr_EditCRM_SYSTEM_PARAMETERS")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, crm_sys_par_id)
                    .setParameter(4, crm_sys_par_client)
                    ;
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
}
