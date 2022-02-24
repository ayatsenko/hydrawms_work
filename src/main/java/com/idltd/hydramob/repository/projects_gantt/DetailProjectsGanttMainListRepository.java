package com.idltd.hydramob.repository.projects_gantt;

import com.idltd.hydramob.entity.projects_gantt.DetailProjectsGanttMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProjectsGanttMainListRepository extends CrudRepository<DetailProjectsGanttMainList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_GANTT_MENU(?1,?2))")
    List<DetailProjectsGanttMainList> queryDetailProjectGanttMainListByID(Long user_id, Long role_id);
}