package com.idltd.hydramob.repository.report_month_debtors;

import com.idltd.hydramob.entity.report_month_debtors.ReportMonthDebtorsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportMonthDebtorsMainRepository extends CrudRepository<ReportMonthDebtorsMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_136_MAIN_MENU(?1,?2,?3))")
    List<ReportMonthDebtorsMain> queryGetMonthDebtorsMain(Long user_id, Long role_id, Long year);
}