package com.idltd.hydramob.repository.report_tender_calendar;

import com.idltd.hydramob.entity.report_tender_calendar.ReportTenderCalendarDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTenderCalendarDetailRepository extends CrudRepository<ReportTenderCalendarDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_126(?1,?2,?3,?4))")
    List<ReportTenderCalendarDetail> queryGetAllRep126(String start_date, String end_date, Long user_id, Long req_cnt_id);
}