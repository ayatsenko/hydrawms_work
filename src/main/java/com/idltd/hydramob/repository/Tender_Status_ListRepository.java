package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Tender_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tender_Status_ListRepository extends CrudRepository<Tender_Status_List, Long> {
    @Query(nativeQuery = true, value = "select * from TENDER_STATUS where tend_status_id in (1,2) ORDER BY TEND_STATUS_ID")
    List<Tender_Status_List> queryNewEditStatusID();

    @Query(nativeQuery = true, value = "select * from TENDER_STATUS where tend_status_id in (1,2,5,6) ORDER BY TEND_STATUS_ID")
    List<Tender_Status_List> querySaleEditStatusID();

    @Query(nativeQuery = true, value = "select * from TENDER_STATUS where tend_status_id in (2,3,4) ORDER BY TEND_STATUS_ID")
    List<Tender_Status_List> queryOpsEditStatusID();

    @Query(nativeQuery = true, value = "select * from TENDER_STATUS where tend_status_id in (4,5,6) ORDER BY TEND_STATUS_ORDER")
    List<Tender_Status_List> queryFinishEditStatusID();

    @Query(nativeQuery = true, value = "select * from TENDER_STATUS where tend_status_id in (1,2,4,5,6) ORDER BY TEND_STATUS_ORDER")
    List<Tender_Status_List> queryFinishTendEditStatusID();
}
