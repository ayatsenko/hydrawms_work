package com.idltd.hydramob.repository.tms_users;

import com.idltd.hydramob.entity.tms_users.DetailTMSUsersChat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSUsersChatRepository extends CrudRepository<DetailTMSUsersChat, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_POLAND_VIEW.VTMS_USERS_CHAT_DETAIL(?1,?2,?3))")
    List<DetailTMSUsersChat> queryDetailTMSUsersChatByID(Long user_id, Long role_id, Long tms_chat_id);
}