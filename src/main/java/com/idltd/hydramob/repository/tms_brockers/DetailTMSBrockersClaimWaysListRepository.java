package com.idltd.hydramob.repository.tms_brockers;

import com.idltd.hydramob.entity.tms_brockers.DetailTMSBrockersClaimWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSBrockersClaimWaysListRepository extends CrudRepository<DetailTMSBrockersClaimWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_WAYS_DETAIL(?1,?2,?3))")
    List<DetailTMSBrockersClaimWaysList> queryClaimWaysDetailListByUserID(Long user_id, Long role_id, Long clmwl_id);
}