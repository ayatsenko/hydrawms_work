package com.idltd.hydramob.repository.tms_task_types;

import com.idltd.hydramob.entity.tms_task_types.MenuTMSTaskType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSTaskTypesRepository extends CrudRepository<MenuTMSTaskType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TASKS_TYPE_MENU(?1,?2))")
    List<MenuTMSTaskType> queryMenuTMSTaskTypeByID(Long user_id, Long role_id);
}