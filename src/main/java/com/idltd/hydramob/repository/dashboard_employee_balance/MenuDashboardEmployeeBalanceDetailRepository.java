package com.idltd.hydramob.repository.dashboard_employee_balance;

import com.idltd.hydramob.entity.dashboard_employee_balance.DashboardEmployeeBalaceDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDashboardEmployeeBalanceDetailRepository extends CrudRepository<DashboardEmployeeBalaceDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_124_NEW(?1,?2,?3,?4,?5))")
    List<DashboardEmployeeBalaceDetail> queryGetAllRep124Temp(String start_date, String end_date, Long user_id, Long role_id, Long req_user_id);
}