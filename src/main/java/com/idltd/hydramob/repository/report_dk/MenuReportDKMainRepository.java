package com.idltd.hydramob.repository.report_dk;

import com.idltd.hydramob.entity.report_dk.MenuReportDKMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportDKMainRepository extends CrudRepository<MenuReportDKMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_134_MAIN_MENU_NEW(?1,?2,?3,?4))")
    List<MenuReportDKMain> queryGetRepActionDetails(
            Long user_id, Long role_id, Long dk_paid_type_id, Long ser_id
    );
}