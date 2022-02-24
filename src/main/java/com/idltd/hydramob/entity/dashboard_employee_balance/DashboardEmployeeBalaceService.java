package com.idltd.hydramob.entity.dashboard_employee_balance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DashboardEmployeeBalaceService {
    @Id
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "FIN_SUM", nullable = false)
    public String fin_sum;

    @Column(name = "FIN_PERSENT", nullable = false)
    public String fin_persent;
}
