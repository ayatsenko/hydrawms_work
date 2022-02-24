package com.idltd.hydramob.repository.auto_tasks;

import com.idltd.hydramob.entity.auto_tasks.MenuAutoTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAutoTasksRepository extends CrudRepository<MenuAutoTask, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_SHEDULER_VIEW.VSHEDULER_EVENT(?1,?2))")
    List<MenuAutoTask> queryMenuAutoTasksAll(Long user_id, Long role_id);
}