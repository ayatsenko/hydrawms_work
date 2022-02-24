package com.idltd.hydramob.controller.kpi_roles;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.kpi_roles.MenuKPIRolesMainRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/kpi_roles")
public class KPIRolesController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuKPIRolesMainRepository menuKPIRolesMainRepository;
    public KPIRolesController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuKPIRolesMainRepository menuKPIRolesMainRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuKPIRolesMainRepository = menuKPIRolesMainRepository;
     }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clm_id", required = false) Long clm_id
    ){
        model.addObject("clm_id", clm_id);

        model.setViewName("kpi_roles/cover");
        return model;
    }

    @PostMapping("/get_main_table")
    public JSONDatatable get_main_table(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuKPIRolesMainRepository.queryKPIRoleMenuByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id));
        return result;
    }
}
