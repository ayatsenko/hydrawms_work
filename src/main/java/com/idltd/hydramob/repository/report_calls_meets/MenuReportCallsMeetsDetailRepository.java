package com.idltd.hydramob.repository.report_calls_meets;

import com.idltd.hydramob.entity.report_calls_meets.ReportCallsMeetsDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportCallsMeetsDetailRepository extends CrudRepository<ReportCallsMeetsDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_123(?1,?2,?3,?4))")
    List<ReportCallsMeetsDetail> queryGetAllRep123(String start_date, String end_date, Long user_id, Long req_user_id);
}