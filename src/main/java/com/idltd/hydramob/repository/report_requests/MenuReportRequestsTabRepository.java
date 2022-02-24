package com.idltd.hydramob.repository.report_requests;

import com.idltd.hydramob.entity.report_requests.ReportRequestsTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportRequestsTabRepository extends CrudRepository<ReportRequestsTab, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_ALL_CUR(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqAllByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_WIN_CUR(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqWinByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_LOSE_CUR(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqLoseByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_CAN_CUR(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqCanByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_FAIL_CUR(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqFailByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_TAB_DETAIL(?1,?2,?3,?4,?5,?6))")
    List<ReportRequestsTab> queryReqTabByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id, Long req_status_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_START(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqStartByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_CONTRACT(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqContractByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_START_CAN(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqStartCanByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_USER_CONTRACT_CAN(?1,?2,?3,?4,?5))")
    List<ReportRequestsTab> queryReqContractCanByUserID(Long user_id, Long ser_id, String startdate, String enddate, Long req_user_id);
}