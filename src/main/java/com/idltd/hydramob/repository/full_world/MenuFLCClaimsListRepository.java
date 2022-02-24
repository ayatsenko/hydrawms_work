package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.MenuClaimsMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLCClaimsListRepository extends CrudRepository<MenuClaimsMainList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLC_MENU_TIME_NEW(?1,?2,?3,?4,?5,?6))")
    List<MenuClaimsMainList> queryNewClaimsFLCMenuListByUserID(
            Long user_id, Long role_id, Long full_world_filter_id, String full_world_filter_start_date, String full_world_filter_end_date, Long ser_id
    );
}