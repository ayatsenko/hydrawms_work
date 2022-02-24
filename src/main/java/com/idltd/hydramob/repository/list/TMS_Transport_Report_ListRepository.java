package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.TMS_Transport_Report_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TMS_Transport_Report_ListRepository extends CrudRepository<TMS_Transport_Report_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_REPORT_VIEW.VREPORT_TRANS(?1,?2))")
    List<TMS_Transport_Report_List> queryTMSTransportReportListByID(Long user_id, Long role_id);
}
