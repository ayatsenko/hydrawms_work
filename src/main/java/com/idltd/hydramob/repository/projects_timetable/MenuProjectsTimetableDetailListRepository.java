package com.idltd.hydramob.repository.projects_timetable;

import com.idltd.hydramob.entity.projects_timetable.MenuProjectsTimetableDetailList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuProjectsTimetableDetailListRepository extends CrudRepository<MenuProjectsTimetableDetailList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_PAY_TT_MENU(?1,?2,?3))")
    List<MenuProjectsTimetableDetailList> queryMenuProjectTimetableDetailListByID(Long user_id, Long role_id, Long year);
}