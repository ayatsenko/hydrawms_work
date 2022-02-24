package com.idltd.hydramob.repository.aero_world;

import com.idltd.hydramob.entity.aero_world.MenuAeroWorldClaimsCostsGroupsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAeroWorldClaimCostsGroupsListRepository extends CrudRepository<MenuAeroWorldClaimsCostsGroupsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_COSTS_GROUP_MENU(?1,?2,?3))")
    List<MenuAeroWorldClaimsCostsGroupsList> queryAeroWorldClaimCostsGroupsMenuListByUserID(
            Long user_id, Long role_id, Long clm_id
    );
}