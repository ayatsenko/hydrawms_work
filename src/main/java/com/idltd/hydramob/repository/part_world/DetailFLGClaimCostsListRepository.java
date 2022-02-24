package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.DetailFLGClaimCostsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailFLGClaimCostsListRepository extends CrudRepository<DetailFLGClaimCostsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_COSTS_DETAIL(?1,?2,?3))")
    List<DetailFLGClaimCostsList> queryFLGClaimsDetailCostsListByUserID(Long user_id, Long role_id, Long clmcl_id);
}