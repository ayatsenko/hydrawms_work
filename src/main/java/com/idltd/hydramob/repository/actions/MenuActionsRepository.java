package com.idltd.hydramob.repository.actions;

import com.idltd.hydramob.entity.actions.ActionMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuActionsRepository extends CrudRepository<ActionMenu, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_ACTION_VIEW.vACTION_USER_MENU_FULL(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11))")
    List<ActionMenu> queryMenuActionsByID(
            Long user_id,
            Long role_id,
            Long action_filter_id,
            String action_filter_start_date,
            String action_filter_end_date,
            Long action_filter_type_id,
            Long action_filter_action_id,
            Long action_filter_status_id,
            Long action_filter_result_id,
            Long action_filter_client_id,
            Long action_filter_user_id
    );
}