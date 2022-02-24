package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.DetailClaimTransList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClaimTransListRepository extends CrudRepository<DetailClaimTransList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TRANS_DETAIL(?1,?2,?3))")
    List<DetailClaimTransList> queryClaimsDetailTransListByUserID(Long user_id, Long role_id, Long clmtrl_id);
}