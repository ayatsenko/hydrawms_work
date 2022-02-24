package com.idltd.hydramob.controller.user_interface_roles;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.user_interface_roles.MenuUserInterfaceRolesRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user_interface_roles")
public class UserInterfaceRolesController {
    private final User_ListRepository user_ListRepository;
    private final MenuUserInterfaceRolesRepository menuUserInterfaceRolesRepository;

    public UserInterfaceRolesController(
            User_ListRepository user_ListRepository,
            MenuUserInterfaceRolesRepository menuUserInterfaceRolesRepository
                                ) {
        this.user_ListRepository = user_ListRepository;
        this.menuUserInterfaceRolesRepository = menuUserInterfaceRolesRepository;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable() {
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        List<User_List> User_List = (List<User_List>) user_ListRepository.queryUserByName(authname);

        result.setData( menuUserInterfaceRolesRepository.queryUserInterfaceRolesByID(User_List.get(0).id));
        return result;
    }
}
