package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.User_Report_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_Report_ListRepository extends CrudRepository<User_Report_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(ZCRM.PKG_REPORTS_VIEW2.VREPORT_USERS(?1)) ORDER BY USER_SURNAME")
    List<User_Report_List> queryGetUserByChiefID(Long user_id);
}