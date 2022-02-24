package com.idltd.hydramob.repository.report_tms_check;

import com.idltd.hydramob.entity.report_tms_check.MenuReportTMSCheckMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTMSCheckMainRepository extends CrudRepository<MenuReportTMSCheckMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_137_MAIN_MENU(?1,?2))")
    List<MenuReportTMSCheckMain> queryGetMainRep137(
            Long user_id, Long role_id
    );
}