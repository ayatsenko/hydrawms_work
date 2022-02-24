package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.MenuFLGClaimWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLGClaimWaysListRepository extends CrudRepository<MenuFLGClaimWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLG_WAYS_MENU(?1,?2,?3))")
    List<MenuFLGClaimWaysList> queryFLGClaimWaysMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}