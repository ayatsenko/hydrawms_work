package com.idltd.hydramob.repository.dashboard_employee_balance;

import com.idltd.hydramob.entity.dashboard_employee_balance.DashboardEmployeeBalaceService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDashboardEmployeeBalanceServiceRepository extends CrudRepository<DashboardEmployeeBalaceService, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_124_SERVICE_DETAIL(?1,?2,?3,?4))")
    List<DashboardEmployeeBalaceService> queryGetAllRep124Service(Long user_id, Long role_id, Long req_user_id, Long month);
}