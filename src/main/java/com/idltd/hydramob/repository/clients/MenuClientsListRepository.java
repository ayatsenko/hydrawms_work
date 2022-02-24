package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsListRepository extends CrudRepository<MenuClientsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_MENU(?1,?2))")
    List<MenuClientsList> queryClientMenuListByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_MENU_FILTER(?1,?2,?3,?4,?5,?6))")
    List<MenuClientsList> queryClientMenuFilterListByUserID(
            Long user_id, Long role_id, Long requests_filter_check, Long clients_filters_user_id, Long clients_filters_status_id, Long clients_filters_class_id
    );
}