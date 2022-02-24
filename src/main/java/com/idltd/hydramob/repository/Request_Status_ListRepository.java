package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Request_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Request_Status_ListRepository extends CrudRepository<Request_Status_List, Long> {
    @Query(nativeQuery = true, value = "select * from REQUEST_STATUS where req_status_id in (1,2) ORDER BY REQ_STATUS_ID")
    List<Request_Status_List> queryNewEditStatusID();

    @Query(nativeQuery = true, value = "select * from REQUEST_STATUS where req_status_id in (1,2,4,5,6) ORDER BY REQ_STATUS_ID")
    List<Request_Status_List> queryAllSimpleEditStatusID();

    @Query(nativeQuery = true, value = "select * from REQUEST_STATUS where req_status_id in (2,3,4) ORDER BY REQ_STATUS_ID")
    List<Request_Status_List> queryOpsEditStatusID();

    @Query(nativeQuery = true, value = "select * from REQUEST_STATUS where req_status_id in (-3,-4,4,5,6,7,8) ORDER BY REQ_STATUS_ORDER")
    List<Request_Status_List> queryFinishEditStatusID();

    @Query(nativeQuery = true, value = "select * from REQUEST_STATUS where req_status_id in (-3,-4,5,7,8) ORDER BY REQ_STATUS_ORDER")
    List<Request_Status_List> queryAfterAllEditStatusID();

    @Query(nativeQuery = true, value = "select * from REQUEST_STATUS where req_status_id in (1,2,4,5) ORDER BY REQ_STATUS_ID")
    List<Request_Status_List> queryFinishTendEditStatusID();
}
