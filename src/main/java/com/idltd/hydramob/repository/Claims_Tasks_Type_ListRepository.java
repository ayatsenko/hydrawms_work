package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Claims_Tasks_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Claims_Tasks_Type_ListRepository extends CrudRepository<Claims_Tasks_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_NEW_TASKS(?1,?2,?3)) ORDER BY CLM_TASK_NAME")
    List<Claims_Tasks_Type_List> queryGetNewTasksListByClaimID(Long user_id, Long role_id, Long clm_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_EDIT_TASKS(?1,?2,?3))  ORDER BY CLM_TASK_NAME")
    List<Claims_Tasks_Type_List> queryGetEditTasksListByClaimID(Long user_id, Long role_id, Long clmtl_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_EDIT_TASKS(?1,?2,?3))  ORDER BY CLM_TASK_NAME")
    List<Claims_Tasks_Type_List> queryGetOneTaskListByClaimID(Long user_id, Long role_id, Long clm_task_id);
}
