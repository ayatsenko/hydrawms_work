package com.idltd.hydramob.repository.report_dk;

import com.idltd.hydramob.entity.report_dk.MenuReportDKService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportDKServiceRepository extends CrudRepository<MenuReportDKService, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_134_SERVICE_MENU(?1,?2,?3))")
    List<MenuReportDKService> queryGetRepDKServiceDetails(Long user_id, Long role_id, Long dk_paid_type_id);
}