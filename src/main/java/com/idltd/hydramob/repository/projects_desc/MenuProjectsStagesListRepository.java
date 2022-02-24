package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.MenuProjectsStagesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuProjectsStagesListRepository extends CrudRepository<MenuProjectsStagesList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_STAGES_MENU_NEW(?1,?2,?3))")
    List<MenuProjectsStagesList> queryMenuProjectStagesListByID(Long user_id, Long role_id, Long parent_pr_id);
}