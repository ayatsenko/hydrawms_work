package com.idltd.hydramob.repository.wms_report_1;

import com.idltd.hydramob.entity.wms_report_1.MenuWMSReport1;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSReport1Repository extends CrudRepository<MenuWMSReport1, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_REPORT_VIEW.VWH_REPORT_1_MENU(?1,?2,?3,?4,?5))")
    List<MenuWMSReport1> queryWMSReport1ByID(
            Long user_id,
            Long role_id,
            Long filter_id,
            String filter_start_date,
            String filter_end_date
    );
}