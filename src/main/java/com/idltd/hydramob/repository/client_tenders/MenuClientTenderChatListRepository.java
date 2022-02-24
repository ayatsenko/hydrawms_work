package com.idltd.hydramob.repository.client_tenders;

import com.idltd.hydramob.entity.client_tenders.MenuClientTenderChatList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientTenderChatListRepository extends CrudRepository<MenuClientTenderChatList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vREQUEST_CHAT_MENU(?1,?2,?3))")
    List<MenuClientTenderChatList> queryClientRequestChatMenuByUserID(Long user_id, Long role_id, Long req_id);
}