package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.MenuClaimWaysTabList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimWaysTabListRepository extends CrudRepository<MenuClaimWaysTabList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLIMS_WAY_TEMP(?1,?2,?3,?4))")
    List<MenuClaimWaysTabList> queryClaimWaysTabMenuListByUserID(Long user_id, Long role_id, Long clm_id, Long order_id);
}