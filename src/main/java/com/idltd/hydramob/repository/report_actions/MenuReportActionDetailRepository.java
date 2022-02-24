package com.idltd.hydramob.repository.report_actions;

import com.idltd.hydramob.entity.report_actions.ReportActionDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportActionDetailRepository extends CrudRepository<ReportActionDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_122(?1,?2,?3,?4))")
    List<ReportActionDetail> queryGetRepActionDetails(String start_date, String end_date, Long user_id, Long req_user_id);
}