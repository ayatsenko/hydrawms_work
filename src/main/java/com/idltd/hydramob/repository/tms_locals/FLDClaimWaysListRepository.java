package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.FLDClaimsWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FLDClaimWaysListRepository extends CrudRepository<FLDClaimsWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_WAYS(?1,?2,?3,?4))")
    List<FLDClaimsWaysList> queryFLDClaimWayListByUserID(Long user_id, Long role_id, Long cnt_id, Long clm_way_id);
}