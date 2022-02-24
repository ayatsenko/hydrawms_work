package com.idltd.hydramob.entity.dashboard_employee_balance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DashboardEmployeeBalaceClients {
    @Id
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "FIN_SUM", nullable = false)
    public String fin_sum;

    @Column(name = "FIN_PERSENT", nullable = false)
    public String fin_persent;
}
