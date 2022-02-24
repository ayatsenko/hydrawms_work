package com.idltd.hydramob.repository.report_requests;

import com.idltd.hydramob.entity.report_requests.ReportRequestsDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportRequestsDetailRepository extends CrudRepository<ReportRequestsDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_127_USER(?1,?2,?3,?4,?5))")
    List<ReportRequestsDetail> queryGetAllRep127(String start_date, String end_date, Long user_id, Long req_ser_id, Long req_user_id);
}