package com.idltd.hydramob.repository.report_emplyee_clients;

import com.idltd.hydramob.entity.report_emplyee_clients.ReportEmployeeClientsDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportEmployeeClientsDetailRepository extends CrudRepository<ReportEmployeeClientsDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_131_LIST_MENU(?1,?2,?3,?4))")
    List<ReportEmployeeClientsDetail> queryGetAllReport131(Long user_id, Long role_id, Long req_user_id, String start_date);
}