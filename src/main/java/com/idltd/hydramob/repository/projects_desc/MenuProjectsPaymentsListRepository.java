package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.MenuProjectsPaymentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuProjectsPaymentsListRepository extends CrudRepository<MenuProjectsPaymentsList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_PAYMENTS_MENU(?1,?2,?3))")
    List<MenuProjectsPaymentsList> queryMenuProjectPaymentsListByID(Long user_id, Long role_id, Long parent_pr_id);
}