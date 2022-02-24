package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.DetailProjectsSchemeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProjectsSchemeListRepository extends CrudRepository<DetailProjectsSchemeList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_PAY_SCHEME_DETAIL(?1,?2,?3))")
    List<DetailProjectsSchemeList> queryDetailProjectPaymentsListByID(Long user_id, Long role_id, Long pr_pay_scheme_id);
}