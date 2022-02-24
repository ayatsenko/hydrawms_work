package com.idltd.hydramob.entity.report_client_balance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportClientBalaceDetail {
    @Id
    public Long cntserl_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_SNAME", nullable = false)
    public String ser_sname;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "FIN_1", nullable = false)
    public String fin_1;

    @Column(name = "FIN_2", nullable = false)
    public String fin_2;

    @Column(name = "FIN_3", nullable = false)
    public String fin_3;

    @Column(name = "FIN_4", nullable = false)
    public String fin_4;

    @Column(name = "FIN_5", nullable = false)
    public String fin_5;

    @Column(name = "FIN_6", nullable = false)
    public String fin_6;

    @Column(name = "FIN_7", nullable = false)
    public String fin_7;

    @Column(name = "FIN_8", nullable = false)
    public String fin_8;

    @Column(name = "FIN_9", nullable = false)
    public String fin_9;

    @Column(name = "FIN_10", nullable = false)
    public String fin_10;

    @Column(name = "FIN_11", nullable = false)
    public String fin_11;

    @Column(name = "FIN_12", nullable = false)
    public String fin_12;

    @Column(name = "FIN_ALL", nullable = false)
    public String fin_all;
}
