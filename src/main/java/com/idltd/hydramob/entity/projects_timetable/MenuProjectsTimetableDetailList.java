package com.idltd.hydramob.entity.projects_timetable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuProjectsTimetableDetailList {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "PR_ID", nullable = false)
    public Long pr_id;

    @Column(name = "PR_SNAME", nullable = false)
    public String pr_sname;

    @Column(name = "PR_PAY_TYPE_ID")
    public Long pr_pay_type_id;

    @Column(name = "PR_PAY_TYPE_SNAME")
    public String pr_pay_type_sname;

    @Column(name = "MONTH_01")
    public String month_01;

    @Column(name = "MONTH_02")
    public String month_02;

    @Column(name = "MONTH_03")
    public String month_03;

    @Column(name = "MONTH_04")
    public String month_04;

    @Column(name = "MONTH_05")
    public String month_05;

    @Column(name = "MONTH_06")
    public String month_06;

    @Column(name = "MONTH_07")
    public String month_07;

    @Column(name = "MONTH_08")
    public String month_08;

    @Column(name = "MONTH_09")
    public String month_09;

    @Column(name = "MONTH_10")
    public String month_10;

    @Column(name = "MONTH_11")
    public String month_11;

    @Column(name = "MONTH_12")
    public String month_12;

    @Column(name = "MONTH_13")
    public String month_13;

    @Column(name = "MONTH_14")
    public String month_14;

    @Column(name = "MONTH_15")
    public String month_15;

    @Column(name = "MONTH_16")
    public String month_16;

    @Column(name = "MONTH_17")
    public String month_17;

    @Column(name = "MONTH_18")
    public String month_18;

    @Column(name = "MONTH_19")
    public String month_19;

    @Column(name = "MONTH_20")
    public String month_20;

    @Column(name = "MONTH_21")
    public String month_21;

    @Column(name = "MONTH_22")
    public String month_22;

    @Column(name = "MONTH_23")
    public String month_23;

    @Column(name = "MONTH_24")
    public String month_24;

    @Column(name = "MONTH_ALL")
    public String month_all;
}
