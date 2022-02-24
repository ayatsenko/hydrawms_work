package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.MenuProjectsFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuProjectsFilesListRepository extends CrudRepository<MenuProjectsFilesList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_FILES_MENU(?1,?2,?3))")
    List<MenuProjectsFilesList> queryDetailProjectFilesListByID(Long user_id, Long role_id, Long parent_pr_id);
}