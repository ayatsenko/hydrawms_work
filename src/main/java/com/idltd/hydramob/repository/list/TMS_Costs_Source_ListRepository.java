package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.TMS_Costs_Source_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TMS_Costs_Source_ListRepository extends CrudRepository<TMS_Costs_Source_List, Long> {
    @Query(nativeQuery = true, value = "select CLMC_SOURCE_ID, CLMC_SOURCE_SNAME from TMS_CLAIMS_COSTS_SOURCE WHERE CLMC_SOURCE_ID = ?1")
    List<TMS_Costs_Source_List> queryTMSCostsSourceListByID(Long tms_contt_id);

    @Query(nativeQuery = true, value = "select CLMC_SOURCE_ID, CLMC_SOURCE_SNAME from TMS_CLAIMS_COSTS_SOURCE")
    List<TMS_Costs_Source_List> queryTMSCostsSourceListAll();
}
