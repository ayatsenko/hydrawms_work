package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.MenuPartWorldClaimWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuPartWorldClaimWaysListRepository extends CrudRepository<MenuPartWorldClaimWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_WAYS_MENU(?1,?2,?3))")
    List<MenuPartWorldClaimWaysList> queryClaimWaysMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}