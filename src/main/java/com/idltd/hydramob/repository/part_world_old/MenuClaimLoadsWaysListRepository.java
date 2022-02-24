package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.MenuClaimLoadsWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimLoadsWaysListRepository extends CrudRepository<MenuClaimLoadsWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_LOADS_WAYS_MENU(?1,?2,?3,?4))")
    List<MenuClaimLoadsWaysList> queryClaimTransWaysMenuListByUserID(Long user_id, Long role_id, Long clm_id, Long clmll_id);
}