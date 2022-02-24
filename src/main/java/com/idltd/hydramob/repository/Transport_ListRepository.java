package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Transport_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Transport_ListRepository extends CrudRepository<Transport_List, Long> {
    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_TMS_VIEW.vTRANSPORT_LIST(?1,?2)) ORDER BY CNT_NAME")
    List<Transport_List> queryTransportByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_TMS_VIEW.vAIR_TRANSPORT_LIST(?1,?2)) ORDER BY CNT_NAME")
    List<Transport_List> queryAirTransportByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_TMS_VIEW.vCONSIGNEE_LIST(?1,?2)) ORDER BY CNT_NAME")
    List<Transport_List> queryConsigneeByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_TMS_VIEW.vSHIPPER_LIST(?1,?2)) ORDER BY CNT_NAME")
    List<Transport_List> queryShipperByUserID(Long user_id, Long role_id);
}
