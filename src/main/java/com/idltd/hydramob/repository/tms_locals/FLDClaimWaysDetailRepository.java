package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.FLDClaimsWaysDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FLDClaimWaysDetailRepository extends CrudRepository<FLDClaimsWaysDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_WAYS_DETAIL(?1,?2,?3))")
    List<FLDClaimsWaysDetail> queryFLDClaimWayDetailByUserID(Long user_id, Long role_id, Long cnt_way_id);
}