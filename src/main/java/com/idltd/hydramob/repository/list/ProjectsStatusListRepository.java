package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ProjectsStatusList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsStatusListRepository extends CrudRepository<ProjectsStatusList, Long> {
    @Query(nativeQuery = true, value = "SELECT PR_STATUS_ID, PR_STATUS_NAME, PR_STATUS_SNAME FROM PROJECT_STATUS")
    List<ProjectsStatusList> queryProjectsStatusListAll();

    @Query(nativeQuery = true, value = "SELECT PR_STATUS_ID, PR_STATUS_NAME, PR_STATUS_SNAME FROM PROJECT_STATUS WHERE PR_STATUS_ID in (0,1)")
    List<ProjectsStatusList> queryProjectsStatusNewByID();

    @Query(nativeQuery = true, value = "SELECT PR_STATUS_ID, PR_STATUS_NAME, PR_STATUS_SNAME FROM PROJECT_STATUS WHERE PR_STATUS_ID = ?1")
    List<ProjectsStatusList> queryProjectsStatusListByID(Long pr_status_id);
}