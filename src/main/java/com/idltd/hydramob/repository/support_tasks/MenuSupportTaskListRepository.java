package com.idltd.hydramob.repository.support_tasks;

import com.idltd.hydramob.entity.support_tasks.MenuSupportTaskList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuSupportTaskListRepository extends CrudRepository<MenuSupportTaskList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_SUPPORT_VIEW.VSUPPORT_TASKS_MENU(?1,?2,?3))")
    List<MenuSupportTaskList> queryGetSupportTaskList(Long user_id, Long role_id, Long show_all);
}