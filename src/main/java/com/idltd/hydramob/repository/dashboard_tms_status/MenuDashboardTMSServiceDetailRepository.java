package com.idltd.hydramob.repository.dashboard_tms_status;

import com.idltd.hydramob.entity.dashboard_tms_status.MenuDashboardTMSStatusDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDashboardTMSServiceDetailRepository extends CrudRepository<MenuDashboardTMSStatusDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_DB_MENU(?1,?2,?3,?4,?5))")
    List<MenuDashboardTMSStatusDetail> queryGetMenuDashboardTMSStatusDetail(
            Long user_id, Long role_id, String start_date, String end_date, Long req_ser_id
    );
}