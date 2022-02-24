package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.MenuFLDClaimCostsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLDClaimCostsListRepository extends CrudRepository<MenuFLDClaimCostsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_COSTS_MENU_NEW(?1,?2,?3,?4))")
    List<MenuFLDClaimCostsList> queryFLDClaimCostsMenuListByUserID(Long user_id, Long role_id, Long clm_id, Long clmcgl_id);
}