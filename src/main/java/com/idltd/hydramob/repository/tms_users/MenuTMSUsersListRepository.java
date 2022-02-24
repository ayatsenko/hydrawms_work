package com.idltd.hydramob.repository.tms_users;

import com.idltd.hydramob.entity.tms_users.MenuTMSUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSUsersListRepository extends CrudRepository<MenuTMSUsersList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_POLAND_VIEW.VTMS_USERS_LIST_MENU(?1,?2))")
    List<MenuTMSUsersList> queryMenuTMSUsersListByID(Long user_id, Long role_id);
}