package com.idltd.hydramob.entity.report_month_debtors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportMonthDebtorsMonth {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CNT_ID")
    public String cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "SER_ID")
    public String ser_id;

    @Column(name = "SER_NAME")
    public String ser_name;

    @Column(name = "CS_USER_ID")
    public String cs_user_id;

    @Column(name = "CS_USER_NAME")
    public String cs_user_name;

    @Column(name = "OPS_USER_ID")
    public String ops_user_id;

    @Column(name = "OPS_USER_NAME")
    public String ops_user_name;

    @Column(name = "FIN_SUM")
    public String fin_sum;
}
