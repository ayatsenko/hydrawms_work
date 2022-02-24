package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.DetailFLDClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailFLDClaimsListRepository extends CrudRepository<DetailFLDClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_DETAIL(?1,?2,?3))")
    List<DetailFLDClaimsList> queryFLDlaimsDetailListByUserID(Long user_id, Long role_id, Long clm_id);
}