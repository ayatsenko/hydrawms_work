package com.idltd.hydramob.repository.report_phonebook;

import com.idltd.hydramob.entity.report_phonebook.ReportPhoneBookDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportPhoneBookDetailRepository extends CrudRepository<ReportPhoneBookDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_128(?1,?2))")
    List<ReportPhoneBookDetail> queryGetAllRep128(Long user_id, Long req_cnt_id);
}