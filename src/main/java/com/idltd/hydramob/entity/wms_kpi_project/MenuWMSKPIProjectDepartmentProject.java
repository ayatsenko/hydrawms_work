package com.idltd.hydramob.entity.wms_kpi_project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSKPIProjectDepartmentProject {
    @Id
    @Column(name = "WMS_PROJECT_ID", nullable = false)
    public Long wms_project_id;

    @Column(name = "WMS_PROJECT_NAME", nullable = false)
    public String wms_project_name;

    @Column(name = "MONTH_1")
    public String month_1;

    @Column(name = "MONTH_1_COLOR")
    public String month_1_color;


    @Column(name = "MONTH_2")
    public String month_2;

    @Column(name = "MONTH_2_COLOR")
    public String month_2_color;

    @Column(name = "MONTH_3")
    public String month_3;

    @Column(name = "MONTH_3_COLOR")
    public String month_3_color;

    @Column(name = "MONTH_4")
    public String month_4;

    @Column(name = "MONTH_4_COLOR")
    public String month_4_color;

    @Column(name = "MONTH_5")
    public String month_5;

    @Column(name = "MONTH_5_COLOR")
    public String month_5_color;

    @Column(name = "MONTH_6")
    public String month_6;

    @Column(name = "MONTH_6_COLOR")
    public String month_6_color;

    @Column(name = "MONTH_7")
    public String month_7;

    @Column(name = "MONTH_7_COLOR")
    public String month_7_color;

    @Column(name = "MONTH_8")
    public String month_8;

    @Column(name = "MONTH_8_COLOR")
    public String month_8_color;

    @Column(name = "MONTH_9")
    public String month_9;

    @Column(name = "MONTH_9_COLOR")
    public String month_9_color;

    @Column(name = "MONTH_10")
    public String month_10;

    @Column(name = "MONTH_10_COLOR")
    public String month_10_color;

    @Column(name = "MONTH_11")
    public String month_11;

    @Column(name = "MONTH_11_COLOR")
    public String month_11_color;

    @Column(name = "MONTH_12")
    public String month_12;

    @Column(name = "MONTH_12_COLOR")
    public String month_12_color;

    @Column(name = "MONTH_13")
    public String month_13;

    @Column(name = "MONTH_13_COLOR")
    public String month_13_color;
}
