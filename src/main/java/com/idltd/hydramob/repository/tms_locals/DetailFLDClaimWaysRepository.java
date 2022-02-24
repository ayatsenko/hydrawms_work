package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.DetailFLDClaimWays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailFLDClaimWaysRepository extends CrudRepository<DetailFLDClaimWays, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_FLG_CLIMS_WAY_DETAIL(?1,?2,?3))")
    List<DetailFLDClaimWays> queryFLDClaimWaysDetailByUserID(
            Long user_id, Long role_id, Long clmwl_id
    );
}