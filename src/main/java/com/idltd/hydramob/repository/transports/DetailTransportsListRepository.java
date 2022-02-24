package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportsListRepository extends CrudRepository<DetailTransportsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTRANSPORT_DETAIL(?1,?2,?3))")
    List<DetailTransportsList> queryTransportListDetaulByUserID(Long user_id, Long role_id, Long cnt_id);
}