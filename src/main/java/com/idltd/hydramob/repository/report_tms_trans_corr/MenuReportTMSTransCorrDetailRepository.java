package com.idltd.hydramob.repository.report_tms_trans_corr;

import com.idltd.hydramob.entity.report_tms_trans_corr.MenuReportTMSTransCorrDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTMSTransCorrDetailRepository extends CrudRepository<MenuReportTMSTransCorrDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_REPORT_VIEW.vTMS_REPORT_5(?1,?2,?3,?4,?5))")
    List<MenuReportTMSTransCorrDetail> queryGetRepTMSTransCorrDetail(Long user_id, Long role_id, String start_date, String end_date, Long req_cnt_id);
}