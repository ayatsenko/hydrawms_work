package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.MenuFLGClaimWHParamList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLGClaimWHParamListRepository extends CrudRepository<MenuFLGClaimWHParamList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_WH_PARAM_MENU(?1,?2,?3,?4))")
    List<MenuFLGClaimWHParamList> queryFLGClaimsWHParamMenuListByUserID(Long user_id, Long role_id, Long clm_id, Long clmwhl_id);
}