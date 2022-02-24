package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.DetailProjectsMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProjectsMainListRepository extends CrudRepository<DetailProjectsMainList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJECTS_USER_DETAIL(?1,?2,?3))")
    List<DetailProjectsMainList> queryDetailProjectMainListByID(Long user_id, Long role_id, Long pr_id);
}