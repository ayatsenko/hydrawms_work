package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.DetailProjectsChatList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProjectsChatListRepository extends CrudRepository<DetailProjectsChatList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_CHAT_DETAIL(?1,?2,?3))")
    List<DetailProjectsChatList> queryDetailProjectChatListByID(Long user_id, Long role_id, Long pr_chat_id);
}