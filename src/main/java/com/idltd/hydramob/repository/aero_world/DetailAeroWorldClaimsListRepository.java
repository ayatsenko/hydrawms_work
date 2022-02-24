package com.idltd.hydramob.repository.aero_world;

import com.idltd.hydramob.entity.aero_world.DetailAeroWorldClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailAeroWorldClaimsListRepository extends CrudRepository<DetailAeroWorldClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FA_DETAIL(?1,?2,?3))")
    List<DetailAeroWorldClaimsList> queryDetailClaimsAeroWorldListByUserID(
            Long user_id, Long role_id, Long clm_id
    );
}