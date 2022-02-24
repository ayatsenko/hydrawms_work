package com.idltd.hydramob.repository.actions;

import com.idltd.hydramob.entity.actions.ActionDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailActionsRepository extends CrudRepository<ActionDetail, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_ACTION_VIEW.vACTION_USER_DETAIL(?1,?2,?3))")
    List<ActionDetail> queryDetailActionsByID(Long user_id, Long role_id, Long act_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_ACTION_VIEW.vACTION_USER_NEW_DETAIL(?1,?2))")
    List<ActionDetail> queryDetailNewActionsByID(Long user_id, Long role_id);
}