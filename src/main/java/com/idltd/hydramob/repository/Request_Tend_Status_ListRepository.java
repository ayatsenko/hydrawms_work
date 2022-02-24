package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Request_Tend_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Request_Tend_Status_ListRepository extends CrudRepository<Request_Tend_Status_List, Long> {

    @Query(nativeQuery = true, value = "select * from REQUEST_TEND_STATUS where REQ_TEND_STATUS_ID in (-1,-2,1,2,3,4,5,6) ORDER BY REQ_TEND_STATUS_ID")
    List<Request_Tend_Status_List> queryNewTendStatusID();

    @Query(nativeQuery = true, value = "select * from REQUEST_TEND_STATUS where REQ_TEND_STATUS_ID in (-1,-2,1,2,3,4,5,6) ORDER BY REQ_TEND_STATUS_ID")
    List<Request_Tend_Status_List> queryFinalTendStatusID();
}
