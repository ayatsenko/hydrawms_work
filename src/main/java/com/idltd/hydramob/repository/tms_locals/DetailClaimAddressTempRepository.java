package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.DetailClaimsAddressTemp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClaimAddressTempRepository extends CrudRepository<DetailClaimsAddressTemp, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_ADRESS_TEMP_DETAIL(?1,?2,?3,?4))")
    List<DetailClaimsAddressTemp> queryFLDClaimAddressTempDetailByUserID(Long user_id, Long role_id, Long clm_way_id, Long order_id);
}