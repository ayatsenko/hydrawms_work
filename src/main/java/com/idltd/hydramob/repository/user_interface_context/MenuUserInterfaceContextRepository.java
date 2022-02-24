package com.idltd.hydramob.repository.user_interface_context;

import com.idltd.hydramob.entity.user_interface_context.MenuUserInterfaceContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserInterfaceContextRepository extends CrudRepository<MenuUserInterfaceContext, Long> {
    @Query(nativeQuery = true, value = "SELECT RN, MCURL_ID, MC_ROW, MC_ID, MC_NAME, MC_DEFAULT, MC_HIDE, MC_LIST_NUM FROM TABLE(PKG_USERS_VIEW.vMENU_CONT_USER_ROLE_LINK(?1,?2,?3))")
    List<MenuUserInterfaceContext> queryUserInterfaceContextByID(Long user_id, Long role_id, Long menu_id);
}