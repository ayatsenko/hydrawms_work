package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.DetailPartWorldClaimWaysTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailPartWorldClaimWaysTabRepository extends CrudRepository<DetailPartWorldClaimWaysTab, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLIMS_WAY_TEMP_DETAIL(?1,?2,?3,?4,?5))")
    List<DetailPartWorldClaimWaysTab> queryClaimWaysTabDetailByUserID(Long user_id, Long role_id, Long clm_id, Long order_id, Long clmwl_id);
}