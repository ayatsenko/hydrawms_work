package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.MenuSeaShipsClaimErrorsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuSeaShipsClaimErrorsListRepository extends CrudRepository<MenuSeaShipsClaimErrorsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_ERROR_MENU(?1,?2,?3))")
    List<MenuSeaShipsClaimErrorsList> queryClaimErrorsMenuListByUserID(
            Long user_id, Long role_id, Long clm_id
    );
}