package com.idltd.hydramob.repository.tms_brockers;

import com.idltd.hydramob.entity.tms_brockers.DetailTMSBrockersClaimLoadsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSBrockersClaimLoadsListRepository extends CrudRepository<DetailTMSBrockersClaimLoadsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_LOADS_DETAIL(?1,?2,?3))")
    List<DetailTMSBrockersClaimLoadsList> queryClaimLoadsMenuListByUserID(Long user_id, Long role_id, Long clmll_id);
}