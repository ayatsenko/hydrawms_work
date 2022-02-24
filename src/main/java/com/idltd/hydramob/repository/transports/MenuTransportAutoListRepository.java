package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportAutoList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportAutoListRepository extends CrudRepository<MenuTransportAutoList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CARS_MENU(?1,?2,?3))")
    List<MenuTransportAutoList> querytransportAutoMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}