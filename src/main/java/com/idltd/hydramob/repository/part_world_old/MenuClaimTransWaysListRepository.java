package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.MenuClaimTransWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimTransWaysListRepository extends CrudRepository<MenuClaimTransWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TRANS_WAYS_MENU(?1,?2,?3,?4))")
    List<MenuClaimTransWaysList> queryClaimTransWaysMenuListByUserID(Long user_id, Long role_id, Long clm_id, Long clmtrl_id);
}