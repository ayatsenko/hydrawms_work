package com.idltd.hydramob.repository.wms_report_2;

import com.idltd.hydramob.entity.wms_report_2.MenuWMSReport2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSReport2Repository extends CrudRepository<MenuWMSReport2, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_REPORT.VWH_REPORT_3(?1,?2,?3,?4,?5))")
    List<MenuWMSReport2> queryWMSReport2ByID(
            Long user_id,
            Long role_id,
            Long filter_id,
            Long year_id,
            Long month_id
    );
}