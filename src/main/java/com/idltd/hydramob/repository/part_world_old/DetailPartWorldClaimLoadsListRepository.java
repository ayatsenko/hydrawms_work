package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.DetailPartWorldClaimLoadsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailPartWorldClaimLoadsListRepository extends CrudRepository<DetailPartWorldClaimLoadsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_LOADS_DETAIL(?1,?2,?3))")
    List<DetailPartWorldClaimLoadsList> queryClaimLoadsMenuListByUserID(Long user_id, Long role_id, Long clmll_id);
}