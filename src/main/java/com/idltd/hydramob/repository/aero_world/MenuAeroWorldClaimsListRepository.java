package com.idltd.hydramob.repository.aero_world;

import com.idltd.hydramob.entity.aero_world.MenuAeroWorldClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAeroWorldClaimsListRepository extends CrudRepository<MenuAeroWorldClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FA_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<MenuAeroWorldClaimsList> queryClaimsAeroWorldMenuListByUserID(
            Long user_id, Long role_id, Long full_world_filter_id, String full_world_filter_start_date, String full_world_filter_end_date, Long ser_id
    );
}