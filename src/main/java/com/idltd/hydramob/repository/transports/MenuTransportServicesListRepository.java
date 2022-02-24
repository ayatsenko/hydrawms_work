package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportServicesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportServicesListRepository extends CrudRepository<MenuTransportServicesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vTRANSPORT_SERVICES_MENU(?1,?2,?3))")
    List<MenuTransportServicesList> queryTransportServicesMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}