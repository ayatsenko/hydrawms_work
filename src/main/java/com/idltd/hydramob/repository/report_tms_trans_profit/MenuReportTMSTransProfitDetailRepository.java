package com.idltd.hydramob.repository.report_tms_trans_profit;

import com.idltd.hydramob.entity.report_tms_trans_profit.MenuReportTMSTransProfitDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTMSTransProfitDetailRepository extends CrudRepository<MenuReportTMSTransProfitDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_REPORT_VIEW.vTMS_REPORT_1(?1,?2,?3,?4,?5))")
    List<MenuReportTMSTransProfitDetail> queryGetRepTMSTransProfitDetail(Long user_id, Long role_id, String start_date, String end_date, Long req_cnt_id);
}