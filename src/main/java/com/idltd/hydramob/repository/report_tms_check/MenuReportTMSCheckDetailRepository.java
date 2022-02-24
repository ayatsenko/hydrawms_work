package com.idltd.hydramob.repository.report_tms_check;

import com.idltd.hydramob.entity.report_tms_check.MenuReportTMSCheckDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportTMSCheckDetailRepository extends CrudRepository<MenuReportTMSCheckDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_137_DETAIL_MENU(?1,?2,?3,?4))")
    List<MenuReportTMSCheckDetail> queryGetDetailRep137(
            Long user_id, Long role_id, Long ser_id, Long month
    );
}