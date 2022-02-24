package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.DetailFLGClaimWays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailFLGClaimWaysRepository extends CrudRepository<DetailFLGClaimWays, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_FLG_CLIMS_WAY_DETAIL(?1,?2,?3))")
    List<DetailFLGClaimWays> queryFLGClaimWaysDetailByUserID(Long user_id, Long role_id, Long clmwl_id);
}