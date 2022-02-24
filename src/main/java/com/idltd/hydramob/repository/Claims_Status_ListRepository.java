package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Claims_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Claims_Status_ListRepository extends CrudRepository<Claims_Status_List, Long> {
    @Query(nativeQuery = true, value = "select * from TMS_CLAIMS_STATUS WHERE CLM_STATUS_ID = ?1")
    List<Claims_Status_List> queryGetStatusListByID(Long clm_status_id);
}
