package com.idltd.hydramob.repository.report_employee_balance;

import com.idltd.hydramob.entity.report_employee_balance.ReportEmployeeBalaceTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportEmployeeBalanceTabRepository extends CrudRepository<ReportEmployeeBalaceTab, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_124_DETAIL_NEW(?1, ?2, ?3, ?4, ?5, ?6))")
    List<ReportEmployeeBalaceTab> queryGetRep124Month(
            String start_date, String end_date, Long user_id, Long role_id, Long req_user_id, Long req_month
    );
}