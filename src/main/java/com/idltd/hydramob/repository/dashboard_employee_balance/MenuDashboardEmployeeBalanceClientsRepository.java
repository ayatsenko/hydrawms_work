package com.idltd.hydramob.repository.dashboard_employee_balance;

import com.idltd.hydramob.entity.dashboard_employee_balance.DashboardEmployeeBalaceClients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDashboardEmployeeBalanceClientsRepository extends CrudRepository<DashboardEmployeeBalaceClients, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_124_CLIENT_DETAIL(?1,?2,?3,?4,?5))")
    List<DashboardEmployeeBalaceClients> queryGetAllRep124Clients(Long user_id, Long role_id, Long req_user_id, Long req_ser_id, Long month);
}