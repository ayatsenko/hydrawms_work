package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.DetailClaimLoadsTemp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClaimLoadsTempRepository extends CrudRepository<DetailClaimLoadsTemp, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_LOADS_TEMP_DETAIL(?1,?2,?3))")
    List<DetailClaimLoadsTemp> queryFLDClaimLoadsTempDetailByUserID(Long user_id, Long role_id, Long clm_load_id);
}