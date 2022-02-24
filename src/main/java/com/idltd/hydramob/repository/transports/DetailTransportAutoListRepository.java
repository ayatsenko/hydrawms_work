package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportAutoList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportAutoListRepository extends CrudRepository<DetailTransportAutoList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CARS_DETAIL(?1,?2,?3))")
    List<DetailTransportAutoList> querytransportAutoDetailListByUserID(Long user_id, Long role_id, Long cntc_id);
}