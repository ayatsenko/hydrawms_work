package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.MenuProjectsSchemeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuProjectsSchemeListRepository extends CrudRepository<MenuProjectsSchemeList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_PAY_SCHEME_MENU(?1,?2,?3,?4))")
    List<MenuProjectsSchemeList> queryMenuProjectSchemeListByID(Long user_id, Long role_id, Long parent_pr_id, Long pr_pay_id);
}