package com.idltd.hydramob.repository.tms_brockers;

import com.idltd.hydramob.entity.tms_brockers.DetailTMSBrockersClaimCostsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSBrockersClaimCostsListRepository extends CrudRepository<DetailTMSBrockersClaimCostsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_COSTS_DETAIL(?1,?2,?3))")
    List<DetailTMSBrockersClaimCostsList> queryClaimsDetailCostsListByUserID(Long user_id, Long role_id, Long clmcl_id);
}