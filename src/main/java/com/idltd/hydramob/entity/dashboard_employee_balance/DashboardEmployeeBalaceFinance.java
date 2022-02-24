package com.idltd.hydramob.entity.dashboard_employee_balance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DashboardEmployeeBalaceFinance {
    @Id
    public Long fin_id;

    @Column(name = "FIN_NAME", nullable = false)
    public String fin_name;

    @Column(name = "FIN_DATE", nullable = false)
    public String fin_date;

    @Column(name = "FIN_MONTH", nullable = false)
    public String fin_month;

    @Column(name = "FIN_WAY_ID", nullable = false)
    public Long fin_way_id;

    @Column(name = "FIN_WAY_NAME", nullable = false)
    public String fin_way_name;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE", nullable = false)
    public String cnt_identifycode;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USERNAME", nullable = false)
    public String username;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "FIN_SUM", nullable = false)
    public String fin_sum;

    @Column(name = "FA_ID", nullable = false)
    public Long fa_id;

    @Column(name = "FAD_ID", nullable = false)
    public Long fad_id;

    @Column(name = "EMPTY_COLUMN", nullable = false)
    public String empty_column;
}
