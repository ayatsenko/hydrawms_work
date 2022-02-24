package com.idltd.hydramob.repository.action_types;

import com.idltd.hydramob.entity.action_types.ActionType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuActionTypesRepository extends CrudRepository<ActionType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_ACTION_TYPES(?1,?2))")
    List<ActionType> queryMenuActionTypeByID(Long user_id, Long role_id);
}