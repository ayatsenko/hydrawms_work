package com.idltd.hydramob.repository.tms_task_types;

import com.idltd.hydramob.entity.tms_task_types.DetailTMSTaskType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSTaskTypesRepository extends CrudRepository<DetailTMSTaskType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TASKS_TYPE_DETAIL(?1,?2,?3))")
    List<DetailTMSTaskType> queryDetailTMSTaskTypeByID(Long user_id, Long role_id, Long clm_task_id);
}