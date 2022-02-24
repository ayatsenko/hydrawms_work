package com.idltd.hydramob.repository.report_tender_calendar;

import com.idltd.hydramob.entity.report_tender_calendar.ReportTenderResultStatusList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderResultStatusRepository extends CrudRepository<ReportTenderResultStatusList, Long> {
    @Query(nativeQuery = true, value = "select REQ_TEND_STATUS_ID, REQ_TEND_STATUS_NAME, REQ_TEND_STATUS_COLOUR from REQUEST_TEND_STATUS ORDER BY REQ_TEND_STATUS_ID")
    List<ReportTenderResultStatusList> queryGetAllResultStatuses();
}