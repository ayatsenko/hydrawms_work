package com.idltd.hydramob.repository.report_month_debtors;

import com.idltd.hydramob.entity.report_month_debtors.ReportMonthDebtorsMonth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportMonthDebtorsMonthRepository extends CrudRepository<ReportMonthDebtorsMonth, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_136_CONTRAGENT_MENU(?1,?2,?3,?4,?5))")
    List<ReportMonthDebtorsMonth> queryGetMonthDebtorsMonth(Long user_id, Long role_id, Long ser_id, Long year, Long month);
}