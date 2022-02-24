package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.MenuProjectsMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuProjectsMainListRepository extends CrudRepository<MenuProjectsMainList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJECTS_USER_MENU_NEW(?1,?2))")
    List<MenuProjectsMainList> queryMenuProjectMainListByID(Long user_id, Long role_id);
}