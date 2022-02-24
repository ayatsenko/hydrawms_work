package com.idltd.hydramob.repository.tenders;

import com.idltd.hydramob.entity.requests.MenuRequestChatList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTenderChatListRepository extends CrudRepository<MenuRequestChatList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TENDERS_VIEW.vREQUEST_CHAT_MENU(?1,?2,?3))")
    List<MenuRequestChatList> queryRequestChatMenuByUserID(Long user_id, Long role_id, Long req_id);
}