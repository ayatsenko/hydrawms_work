package com.idltd.hydramob.repository.report_tender_calendar;

import com.idltd.hydramob.entity.report_tender_calendar.ReportTenderStatusList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderStatusRepository extends CrudRepository<ReportTenderStatusList, Long> {
    @Query(nativeQuery = true, value = "select TEND_STATUS_ID, TEND_STATUS_SNAME, TEND_STATUS_COLOUR from TENDER_STATUS WHERE TEND_STATUS_ID >= 1 ORDER BY TEND_STATUS_ORDER")
    List<ReportTenderStatusList> queryGetAllStatuses();
}