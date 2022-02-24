package com.idltd.hydramob.repository.user_interface_roles;

import com.idltd.hydramob.entity.user_interface_roles.MenuUserInterfaceRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserInterfaceRolesRepository extends CrudRepository<MenuUserInterfaceRoles, Long> {
    @Query(nativeQuery = true, value = "SELECT UR_ID, ROLE_ID, ROLE_NAME FROM TABLE(PKG_USERS_VIEW.VUSER_ROLES(?1))")
    List<MenuUserInterfaceRoles> queryUserInterfaceRolesByID(Long user_id);
}