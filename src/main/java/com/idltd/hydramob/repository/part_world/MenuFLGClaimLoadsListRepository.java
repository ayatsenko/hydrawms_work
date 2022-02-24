package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.MenuFLGClaimLoadsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLGClaimLoadsListRepository extends CrudRepository<MenuFLGClaimLoadsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_LOADS_MENU(?1,?2,?3))")
    List<MenuFLGClaimLoadsList> queryClaimLoadsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}