package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.DetailClaimWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClaimWaysListRepository extends CrudRepository<DetailClaimWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_WAYS_DETAIL(?1,?2,?3))")
    List<DetailClaimWaysList> queryClaimWaysDetailListByUserID(Long user_id, Long role_id, Long clmwl_id);
}