package com.idltd.hydramob.repository.aero_world;

import com.idltd.hydramob.entity.aero_world.MenuAeroWorldClaimsCostsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAeroWorldClaimsCostsListRepository extends CrudRepository<MenuAeroWorldClaimsCostsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_COSTS_MENU_NEW(?1,?2,?3,?4))")
    List<MenuAeroWorldClaimsCostsList> queryAeroWorldClaimCostsMenuListByUserID(
            Long user_id, Long role_id, Long clm_id, Long clmcgl_id
    );
}