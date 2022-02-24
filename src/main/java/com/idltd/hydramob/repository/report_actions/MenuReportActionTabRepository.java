package com.idltd.hydramob.repository.report_actions;

import com.idltd.hydramob.entity.report_actions.ReportActionTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportActionTabRepository extends CrudRepository<ReportActionTab, Long> {

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ACTION_VIEW.VACTION_USER_MEETALL(?1,?2,?3)) ORDER BY ACT_ID")
    List<ReportActionTab> queryMeetAllByUserID(Long user_id, String startdate, String enddate);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ACTION_VIEW.VACTION_USER_MEETSUC(?1,?2,?3)) ORDER BY ACT_ID")
    List<ReportActionTab> queryMeetSucByUserID(Long user_id, String startdate, String enddate);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ACTION_VIEW.VACTION_USER_MEETFAIL(?1,?2,?3)) ORDER BY ACT_ID")
    List<ReportActionTab> queryMeetFailByUserID(Long user_id, String startdate, String enddate);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ACTION_VIEW.VACTION_USER_CALLALL(?1,?2,?3)) ORDER BY ACT_ID")
    List<ReportActionTab> queryCallAllByUserID(Long user_id, String startdate, String enddate);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ACTION_VIEW.VACTION_USER_CALLSUC(?1,?2,?3)) ORDER BY ACT_ID")
    List<ReportActionTab> queryCallSucByUserID(Long user_id, String startdate, String enddate);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ACTION_VIEW.VACTION_USER_CALLFAIL(?1,?2,?3)) ORDER BY ACT_ID")
    List<ReportActionTab> queryCallFailByUserID(Long user_id, String startdate, String enddate);;
}