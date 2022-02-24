package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientsWHServicesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsWHServicesListRepository extends CrudRepository<MenuClientsWHServicesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.VEXCHANGE_SERVICE_TYPE_LINK_MENU(?1,?2,?3))")
    List<MenuClientsWHServicesList> queryClientMenuWHServicesListByUserID(
            Long user_id, Long role_id, Long cnt_id
    );
}