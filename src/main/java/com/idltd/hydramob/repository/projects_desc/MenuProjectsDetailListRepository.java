package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.MenuProjectsDetailList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuProjectsDetailListRepository extends CrudRepository<MenuProjectsDetailList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_DETAIL_USER_MENU(?1,?2,?3))")
    List<MenuProjectsDetailList> queryMenuProjectDetailListByID(Long user_id, Long role_id, Long pr_id);
}