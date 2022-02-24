package com.idltd.hydramob.entity.dep_kpi_plans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDepKPIPlansMainList {
    @Id
    @Column(name = "DEP_ID", nullable = false)
    public Long dep_id;

    @Column(name = "DEP_NAME")
    public String dep_name;

    @Column(name = "MONTH_1")
    public String month_1;

    @Column(name = "MONTH_2")
    public String month_2;

    @Column(name = "MONTH_3")
    public String month_3;

    @Column(name = "MONTH_4")
    public String month_4;

    @Column(name = "MONTH_5")
    public String month_5;

    @Column(name = "MONTH_6")
    public String month_6;

    @Column(name = "MONTH_7")
    public String month_7;

    @Column(name = "MONTH_8")
    public String month_8;

    @Column(name = "MONTH_9")
    public String month_9;

    @Column(name = "MONTH_10")
    public String month_10;

    @Column(name = "MONTH_11")
    public String month_11;

    @Column(name = "MONTH_12")
    public String month_12;
}
