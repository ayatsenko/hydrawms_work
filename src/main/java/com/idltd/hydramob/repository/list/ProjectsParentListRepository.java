package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ProjectsParentList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsParentListRepository extends CrudRepository<ProjectsParentList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_PARENT_LIST(?1,?2,?3))")
    List<ProjectsParentList> queryProjectPriorListAll(Long user_id, Long role_id, Long pr_id);
}