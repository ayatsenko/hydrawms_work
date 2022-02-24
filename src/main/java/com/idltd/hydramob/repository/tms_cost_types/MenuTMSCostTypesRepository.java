package com.idltd.hydramob.repository.tms_cost_types;

import com.idltd.hydramob.entity.tms_cost_types.MenuTMSCostType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSCostTypesRepository extends CrudRepository<MenuTMSCostType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_COSTS_TYPE_MENU(?1,?2))")
    List<MenuTMSCostType> queryMenuTMSCostTypeByID(Long user_id, Long role_id);
}