package com.idltd.hydramob.repository.tms_users;

import com.idltd.hydramob.entity.tms_users.MenuTMSUsersChat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSUsersChatRepository extends CrudRepository<MenuTMSUsersChat, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_POLAND_VIEW.VTMS_USERS_CHAT_MENU(?1,?2,?3))")
    List<MenuTMSUsersChat> queryMenuTMSUsersChatByID(Long user_id, Long role_id, Long req_user_id);
}