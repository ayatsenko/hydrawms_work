package com.idltd.hydramob.repository.user_interface_menu;

import com.idltd.hydramob.entity.user_interface_menu.MenuUserInterfaceMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserInterfaceMenuRepository extends CrudRepository<MenuUserInterfaceMenu, Long> {
    @Query(nativeQuery = true, value = "SELECT MURL_ID, MENU_ID, MENU_NAME, MR_HIDE FROM TABLE(PKG_USERS_VIEW.vMENU_USER_ROLE_LINK(?1,?2))")
    List<MenuUserInterfaceMenu> queryUserInterfaceMenuByID(Long user_id, Long role_id);
}