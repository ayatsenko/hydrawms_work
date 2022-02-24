package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientsWHList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsWHListRepository extends CrudRepository<MenuClientsWHList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTRAGENT_WMS_LINK_MENU(?1,?2,?3))")
    List<MenuClientsWHList> queryClientMenuWHListByUserID(Long user_id, Long role_id, Long cnt_id);
}