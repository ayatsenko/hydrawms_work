package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ProjectsTypeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsTypeListRepository extends CrudRepository<ProjectsTypeList, Long> {
    @Query(nativeQuery = true, value = "SELECT PR_TYPE_ID, PR_TYPE_NAME, PR_TYPE_SNAME FROM PROJECT_TYPE")
    List<ProjectsTypeList> queryProjectTypeListAll();
}