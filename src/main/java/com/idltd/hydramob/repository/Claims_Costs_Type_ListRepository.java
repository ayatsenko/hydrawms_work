package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Claims_Costs_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Claims_Costs_Type_ListRepository extends CrudRepository<Claims_Costs_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_COSTS_TYPE WHERE CLMC_TYPE_ID not in (1,8,11)")
    List<Claims_Costs_Type_List> queryNewCostTypeShortListByID();

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_COSTS_TYPE WHERE CLMC_TYPE_ID not in (1,8)")
    List<Claims_Costs_Type_List> queryNewCostTypeFullListByID();

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_COSTS_TYPE WHERE CLMC_SOURCE_ID = ?1 AND VT_ID <> 1 AND CLMC_TYPE_ID <> 8")
    List<Claims_Costs_Type_List> queryNewCostTypeSourceListByID(Long clmc_source_id);

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_COSTS_TYPE WHERE CLMC_TYPE_ID = ?1")
    List<Claims_Costs_Type_List> queryOneCostTypeListByID(Long clmc_type_id);

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_COSTS_TYPE WHERE CLMC_SOURCE_ID = ?1 AND CLMC_TYPE_ID <> 8")
    List<Claims_Costs_Type_List> queryCostTypeAllListByID(Long clmc_source_id);

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_COSTS_TYPE WHERE TRANS_CHECK_ID = ?1 AND VT_ID <> 1 AND CLMC_TYPE_ID <> 8")
    List<Claims_Costs_Type_List> queryNewCostTypeSourceListByTransID(Long trans_costs_group);

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_COSTS_TYPE WHERE TRANS_CHECK_ID = ?1 AND CLMC_TYPE_ID <> 8")
    List<Claims_Costs_Type_List> queryCostTypeAllListByTransID(Long trans_costs_group);
}
