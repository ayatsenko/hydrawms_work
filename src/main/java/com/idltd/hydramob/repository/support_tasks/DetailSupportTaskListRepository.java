package com.idltd.hydramob.repository.support_tasks;

import com.idltd.hydramob.entity.support_tasks.DetailSupportTaskList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailSupportTaskListRepository extends CrudRepository<DetailSupportTaskList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_SUPPORT_VIEW.VSUPPORT_TASKS_DETAIL(?1,?2,?3))")
    List<DetailSupportTaskList> queryGetSupportTaskListDetail(Long user_id, Long role_id, Long spt_id);
}