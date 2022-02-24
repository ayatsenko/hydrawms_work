package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.SupportTaskStatusList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTaskStatusListRepository extends CrudRepository<SupportTaskStatusList, Long> {
    @Query(nativeQuery = true, value = "select SPTS_ID, SPTS_NAME, SPTS_SNAME, SPTS_DESCRIPTION, SPTS_COLOR from SUPPORT_TASK_STATUS WHERE SPTS_ID = ?1")
    List<SupportTaskStatusList> querySupportTaskTypeStatusByID(Long sptp_id);

    @Query(nativeQuery = true, value = "select SPTS_ID, SPTS_NAME, SPTS_SNAME, SPTS_DESCRIPTION, SPTS_COLOR from SUPPORT_TASK_STATUS")
    List<SupportTaskStatusList> querySupportTaskStatusListAll();
}
