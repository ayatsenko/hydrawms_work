package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Request_Result_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Request_Result_Status_ListRepository extends CrudRepository<Request_Result_Status_List, Long> {
    @Query(nativeQuery = true, value = "select * from REQUEST_RESULT_STATUS ORDER BY REQ_RESULT_STATUS_ID")
    List<Request_Result_Status_List> queryEndResultStatusAll();

    @Query(nativeQuery = true, value = "select * from REQUEST_RESULT_STATUS WHERE REQ_RESULT_STATUS_ID in (1,2,3,4)")
    List<Request_Result_Status_List> queryEndResultStatusID();

    @Query(nativeQuery = true, value = "select * from REQUEST_RESULT_STATUS WHERE REQ_RESULT_STATUS_ID = ?1")
    List<Request_Result_Status_List> queryEndResultStatusOneByID(Long req_result_status_id);
}
