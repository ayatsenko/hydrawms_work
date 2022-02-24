package com.idltd.hydramob.repository.report_tenders;

import com.idltd.hydramob.entity.report_tenders.ReportTendersTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTendersTabRepository extends CrudRepository<ReportTendersTab, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REPORTS_VIEW2.VREPORT_129_DETAIL_USER(?1,?2,?3,?4,?5,?6,?7))")
    List<ReportTendersTab> queryReportTenderTabByUserID(String startdate, String enddate, Long user_id,  Long role_id, Long type_id, Long req_ser_id, Long req_user_id);
}