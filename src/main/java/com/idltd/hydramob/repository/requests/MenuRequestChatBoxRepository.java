package com.idltd.hydramob.repository.requests;

import com.idltd.hydramob.entity.requests.MenuRequestChatBox;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestChatBoxRepository extends CrudRepository<MenuRequestChatBox, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REQUEST_VIEW.vREQUEST_CHAT_BOX_MENU(?1,?2,?3))")
    List<MenuRequestChatBox> queryRequestChatBoxByUserID(
            Long user_id, Long role_id, Long req_id
    );
}