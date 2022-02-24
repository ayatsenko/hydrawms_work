package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.DetailNewFLCClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailNewFLCClaimsListRepository extends CrudRepository<DetailNewFLCClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_FLC_CLAIMS_NEW_DETAIL(?1,?2))")
    List<DetailNewFLCClaimsList> queryNewFLCClaimsDetailListByUserID(Long user_id, Long role_id);
}