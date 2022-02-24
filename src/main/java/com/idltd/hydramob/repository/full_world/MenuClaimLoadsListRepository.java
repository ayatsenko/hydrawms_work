package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.MenuClaimLoadsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimLoadsListRepository extends CrudRepository<MenuClaimLoadsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_LOADS_MENU(?1,?2,?3))")
    List<MenuClaimLoadsList> queryClaimLoadsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}