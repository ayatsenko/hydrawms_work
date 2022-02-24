package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.DetailFLDClaimTransList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailFLDClaimTransListRepository extends CrudRepository<DetailFLDClaimTransList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TRANS_DETAIL(?1,?2,?3))")
    List<DetailFLDClaimTransList> queryFLDClaimsDetailTransListByUserID(
            Long user_id, Long role_id, Long clmtrl_id
    );
}