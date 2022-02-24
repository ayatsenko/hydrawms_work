package com.idltd.hydramob.repository.dashboard_employee_balance;

import com.idltd.hydramob.entity.dashboard_employee_balance.DashboardEmployeeBalaceFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDashboardEmployeeBalanceFinanceRepository extends CrudRepository<DashboardEmployeeBalaceFinance, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.VREPORT_124_FINANCE_DETAIL(?1,?2,?3,?4,?5,?6))")
    List<DashboardEmployeeBalaceFinance> queryGetAllRep124Finance(Long user_id, Long role_id, Long req_user_id, Long req_ser_id, Long req_cnt_id, Long month);
}