package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportsListRepository extends CrudRepository<MenuTransportsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTRANSPORT_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuTransportsList> querytransportMenuListByUserID(Long user_id, Long role_id, Long country_id, String req_address, Long req_tir_check, Long req_adr_check, Long req_ekmt_check);
}