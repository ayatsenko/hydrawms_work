package com.idltd.hydramob.controller.user_interface_menu;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.user_interface_menu.MenuUserInterfaceMenuRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user_interface_menu")
public class UserInterfaceMenuController {
    private final User_ListRepository user_ListRepository;
    private final MenuUserInterfaceMenuRepository menuUserInterfaceMenuRepository;

    public UserInterfaceMenuController(
            User_ListRepository user_ListRepository,
            MenuUserInterfaceMenuRepository menuUserInterfaceMenuRepository
                                ) {
        this.user_ListRepository = user_ListRepository;
        this.menuUserInterfaceMenuRepository = menuUserInterfaceMenuRepository;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable(
            @RequestParam(value="role_id") Long role_id
    ) {
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        List<User_List> User_List = user_ListRepository.queryUserByName(authname);
        if(role_id != null) {
            result.setData(menuUserInterfaceMenuRepository.queryUserInterfaceMenuByID(User_List.get(0).id, role_id));
        }
        return result;
    }
}
