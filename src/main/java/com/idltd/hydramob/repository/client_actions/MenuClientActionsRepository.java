package com.idltd.hydramob.repository.client_actions;

import com.idltd.hydramob.entity.client_actions.ClientActionMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientActionsRepository extends CrudRepository<ClientActionMenu, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_ACTION_VIEW.vACTION_CLIENT_USER_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<ClientActionMenu> queryMenuClientActionsByID(
            Long user_id, Long role_id, Long cnt_id, Long client_actions_filter_id, String client_actions_filter_start_date, String client_actions_filter_end_date
    );
}