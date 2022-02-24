package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.TMS_Claims_Way_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Claims_Ways_Type_ListRepository extends CrudRepository<TMS_Claims_Way_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_WAY_TYPE WHERE CLM_WAY_ID = ?1")
    List<TMS_Claims_Way_Type_List> queryClientWayTypeByID(Long clm_way_id);

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_FLG_WAY_TYPE WHERE CLM_WAY_ID in (1,2,7,6,4,5) ORDER BY CLM_WAY_ORDERS")
    List<TMS_Claims_Way_Type_List> queryFLGClientWayTypeAll();

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_FLG_WAY_TYPE WHERE CLM_WAY_ID not in (7,6) ORDER BY CLM_WAY_ORDERS")
    List<TMS_Claims_Way_Type_List> queryFLCClientWayTypeAll();

    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_FLG_WAY_TYPE WHERE CLM_WAY_ID in (1,5) ORDER BY CLM_WAY_ORDERS")
    List<TMS_Claims_Way_Type_List> queryFLDClientWayTypeAll();
}
