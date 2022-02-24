package com.idltd.hydramob.repository.client_actions;

import com.idltd.hydramob.entity.client_actions.ClientActionDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientActionsRepository extends CrudRepository<ClientActionDetail, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_ACTION_VIEW.vACTION_USER_DETAIL(?1,?2, ?3))")
    List<ClientActionDetail> queryMenuActionsByID(Long user_id, Long role_id, Long act_id);
}