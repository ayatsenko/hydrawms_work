package com.idltd.hydramob.repository.report_service_balance;

import com.idltd.hydramob.entity.report_service_balance.ReportServiceDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportServiceDetailRepository extends CrudRepository<ReportServiceDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_130_MENU_NEW(?1,?2,?3,?4,?5))")
    List<ReportServiceDetail> queryGetAllRep130(String start_date, String end_date, Long user_id, Long role_id, Long req_ser_id);
}