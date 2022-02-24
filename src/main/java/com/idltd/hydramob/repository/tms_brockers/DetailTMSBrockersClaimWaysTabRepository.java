package com.idltd.hydramob.repository.tms_brockers;

import com.idltd.hydramob.entity.tms_brockers.DetailTMSBrockersClaimWaysTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSBrockersClaimWaysTabRepository extends CrudRepository<DetailTMSBrockersClaimWaysTab, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLIMS_WAY_TEMP_DETAIL(?1,?2,?3,?4,?5))")
    List<DetailTMSBrockersClaimWaysTab> queryClaimWaysTabDetailByUserID(Long user_id, Long role_id, Long clm_id, Long order_id, Long clmwl_id);
}