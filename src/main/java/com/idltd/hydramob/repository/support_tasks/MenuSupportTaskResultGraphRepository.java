package com.idltd.hydramob.repository.support_tasks;

import com.idltd.hydramob.entity.support_tasks.MenuSupportTaskResultGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuSupportTaskResultGraphRepository extends CrudRepository<MenuSupportTaskResultGraph, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_SUPPORT_VIEW.VSUPPORT_TASKS_RESULT_GRAPH(?1,?2,?3,?4))")
    List<MenuSupportTaskResultGraph> queryGetSupportTaskResultGraph(Long user_id, Long role_id, String start_date, String end_date);
}