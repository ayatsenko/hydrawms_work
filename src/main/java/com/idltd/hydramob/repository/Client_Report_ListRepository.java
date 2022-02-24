package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Client_Report_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Client_Report_ListRepository extends CrudRepository<Client_Report_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(ZCRM.PKG_REPORTS_VIEW2.VREPORT_CONTRAGENTS(?1)) ORDER BY CNT_NAME")
    List<Client_Report_List> queryGetClientByChiefID(Long user_id);
}