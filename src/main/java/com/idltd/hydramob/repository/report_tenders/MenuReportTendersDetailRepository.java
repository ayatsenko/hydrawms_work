package com.idltd.hydramob.repository.report_tenders;

import com.idltd.hydramob.entity.report_tenders.ReportTendersDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTendersDetailRepository extends CrudRepository<ReportTendersDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_129_USER(?1,?2,?3,?4,?5,?6))")
    List<ReportTendersDetail> queryGetAllRep129(String start_date, String end_date, Long user_id, Long role_id, Long req_ser_id, Long req_user_id);
}