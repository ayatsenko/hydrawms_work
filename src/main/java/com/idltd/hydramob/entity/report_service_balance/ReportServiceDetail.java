package com.idltd.hydramob.entity.report_service_balance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportServiceDetail {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "FIN_1_COUNT")
    public String fin_1_count;

    @Column(name = "FIN_1")
    public String fin_1;

    @Column(name = "FIN_2_COUNT")
    public String fin_2_count;

    @Column(name = "FIN_2")
    public String fin_2;

    @Column(name = "FIN_3_COUNT")
    public String fin_3_count;

    @Column(name = "FIN_3")
    public String fin_3;

    @Column(name = "FIN_4_COUNT")
    public String fin_4_count;

    @Column(name = "FIN_4")
    public String fin_4;

    @Column(name = "FIN_5_COUNT")
    public String fin_5_count;

    @Column(name = "FIN_5")
    public String fin_5;

    @Column(name = "FIN_6_COUNT")
    public String fin_6_count;

    @Column(name = "FIN_6")
    public String fin_6;

    @Column(name = "FIN_7_COUNT")
    public String fin_7_count;

    @Column(name = "FIN_7")
    public String fin_7;

    @Column(name = "FIN_8_COUNT")
    public String fin_8_count;

    @Column(name = "FIN_8")
    public String fin_8;

    @Column(name = "FIN_9_COUNT")
    public String fin_9_count;

    @Column(name = "FIN_9")
    public String fin_9;

    @Column(name = "FIN_10_COUNT")
    public String fin_10_count;

    @Column(name = "FIN_10")
    public String fin_10;

    @Column(name = "FIN_11_COUNT")
    public String fin_11_count;

    @Column(name = "FIN_11")
    public String fin_11;

    @Column(name = "FIN_12_COUNT")
    public String fin_12_count;

    @Column(name = "FIN_12")
    public String fin_12;

    @Column(name = "FIN_ALL_COUNT")
    public String fin_all_count;

    @Column(name = "FIN_ALL")
    public String fin_all;
}
