package com.idltd.hydramob.repository.report_requests;

import com.idltd.hydramob.entity.report_requests.ReportRequestsFailTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportRequestsFailTabRepository extends CrudRepository<ReportRequestsFailTab, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_FAIL_CUR(?1,?2,?3,?4,?5))")
    List<ReportRequestsFailTab> queryReqFailByUserID(
            Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id
    );
}