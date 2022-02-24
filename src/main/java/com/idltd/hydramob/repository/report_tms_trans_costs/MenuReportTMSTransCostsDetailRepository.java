package com.idltd.hydramob.repository.report_tms_trans_costs;

import com.idltd.hydramob.entity.report_tms_trans_costs.MenuReportTMSTransCostsDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTMSTransCostsDetailRepository extends CrudRepository<MenuReportTMSTransCostsDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_REPORT_VIEW.vTMS_REPORT_3(?1,?2,?3,?4,?5,?6))")
    List<MenuReportTMSTransCostsDetail> queryGetRepTMSTransCostsDetail(Long user_id, Long role_id, String start_date, String end_date, Long req_cnt_id, Long req_user_id);
}