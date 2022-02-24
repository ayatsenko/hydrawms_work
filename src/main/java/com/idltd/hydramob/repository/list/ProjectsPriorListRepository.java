package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ProjectsPriorList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsPriorListRepository extends CrudRepository<ProjectsPriorList, Long> {
    @Query(nativeQuery = true, value = "SELECT PR_PRIOR_ID, PR_PRIOR_NAME, PR_PRIOR_SNAME FROM PROJECT_PRIOR")
    List<ProjectsPriorList> queryProjectPriorListAll();
}