package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.DetailFLGClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailFLGClaimsListRepository extends CrudRepository<DetailFLGClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_DETAIL(?1,?2,?3))")
    List<DetailFLGClaimsList> queryClaimsDetailListByUserID(Long user_id, Long role_id, Long clm_id);
}