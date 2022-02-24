package com.idltd.hydramob.repository.client_requests;

import com.idltd.hydramob.entity.client_requests.MenuClientRequestChatList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientRequestChatListRepository extends CrudRepository<MenuClientRequestChatList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vREQUEST_CHAT_MENU(?1,?2,?3))")
    List<MenuClientRequestChatList> queryClientRequestChatMenuByUserID(Long user_id, Long role_id, Long req_id);
}