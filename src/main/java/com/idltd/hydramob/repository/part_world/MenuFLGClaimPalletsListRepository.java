package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.MenuFLGClaimPalletsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLGClaimPalletsListRepository extends CrudRepository<MenuFLGClaimPalletsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_PALLETS_MENU(?1,?2,?3))")
    List<MenuFLGClaimPalletsList> queryClaimPalletsMenuListByUserID(Long user_id, Long role_id, Long clmll_id);
}