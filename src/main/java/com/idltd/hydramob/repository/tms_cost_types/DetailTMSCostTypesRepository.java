package com.idltd.hydramob.repository.tms_cost_types;

import com.idltd.hydramob.entity.tms_cost_types.DetailTMSCostType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSCostTypesRepository extends CrudRepository<DetailTMSCostType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_COSTS_TYPE_DETAIL(?1,?2,?3))")
    List<DetailTMSCostType> queryDetailTMSCostTypeByID(Long user_id, Long role_id, Long clmc_type_id);
}