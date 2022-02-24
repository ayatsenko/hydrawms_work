package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.DetailClaimWaysTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClaimWaysTabRepository extends CrudRepository<DetailClaimWaysTab, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLIMS_WAY_TEMP_DETAIL(?1,?2,?3,?4,?5))")
    List<DetailClaimWaysTab> queryClaimWaysTabDetailByUserID(Long user_id, Long role_id, Long clm_id, Long order_id, Long clmwl_id);
}