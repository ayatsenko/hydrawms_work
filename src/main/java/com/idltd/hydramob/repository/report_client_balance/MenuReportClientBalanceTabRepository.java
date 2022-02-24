package com.idltd.hydramob.repository.report_client_balance;

import com.idltd.hydramob.entity.report_client_balance.ReportClientBalaceTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportClientBalanceTabRepository extends CrudRepository<ReportClientBalaceTab, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_125_DETAIL_TEMP(?1,?2,?3,?4,?5,?6,?7))")
    List<ReportClientBalaceTab> queryGetRep125Month(String start_date, String end_date, Long user_id, Long role_id, Long cnt_id, Long ser_id, Long req_month);
}