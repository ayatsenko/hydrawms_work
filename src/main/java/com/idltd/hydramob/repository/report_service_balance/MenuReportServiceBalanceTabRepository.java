package com.idltd.hydramob.repository.report_service_balance;

import com.idltd.hydramob.entity.report_employee_balance.ReportEmployeeBalaceTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportServiceBalanceTabRepository extends CrudRepository<ReportEmployeeBalaceTab, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_130_DETAIL_NEW(?1,?2,?3,?4,?5,?6))")
    List<ReportEmployeeBalaceTab> queryGetRep130Month(String start_date, String end_date, Long user_id, Long role_id, Long req_ser_id, Long req_month);
}