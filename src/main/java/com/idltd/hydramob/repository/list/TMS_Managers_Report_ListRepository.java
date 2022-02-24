package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.TMS_Managers_Report_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TMS_Managers_Report_ListRepository extends CrudRepository<TMS_Managers_Report_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_REPORT_VIEW.VREPORT_MANAGERS(?1,?2))")
    List<TMS_Managers_Report_List> queryTMSManagersReportListByID(Long user_id, Long role_id);
}
