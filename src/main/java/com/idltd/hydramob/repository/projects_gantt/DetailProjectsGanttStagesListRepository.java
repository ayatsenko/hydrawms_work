package com.idltd.hydramob.repository.projects_gantt;

import com.idltd.hydramob.entity.projects_gantt.DetailProjectsGanttStagesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProjectsGanttStagesListRepository extends CrudRepository<DetailProjectsGanttStagesList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_GANTT_STAGE_MENU(?1,?2,?3))")
    List<DetailProjectsGanttStagesList> queryDetailProjectGanttStagesListByID(Long user_id, Long role_id, Long parent_pr_id);
}