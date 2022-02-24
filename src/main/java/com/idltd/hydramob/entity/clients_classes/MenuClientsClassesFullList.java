package com.idltd.hydramob.entity.clients_classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientsClassesFullList {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CNT_SNAME")
    public String cnt_sname;

    @Column(name = "CNT_IDENTIFYCODE")
    public String cnt_identifycode;

    @Column(name = "CS_SNAME")
    public String cs_sname;

    @Column(name = "CS_COLOR")
    public String cs_color;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "CNT_USER_ACCESS")
    public Long cnt_user_access;

    @Column(name = "CNT_USER_MAIN_ACCESS")
    public Long cnt_user_main_access;

    @Column(name = "CNT_USER_SHOW")
    public Long cnt_user_show;

    @Column(name = "CNT_CREATEDATE")
    public String cnt_createdate;

    @Column(name = "VT_ID")
    public Long vt_id;

    @Column(name = "CNT_CLASS_ID")
    public Long cnt_class_id;

    @Column(name = "CNT_CLASS_SNAME")
    public String cnt_class_sname;

    @Column(name = "CNT_CLASS_COLOR")
    public String cnt_class_color;

    @Column(name = "CALLS_CURRENT")
    public String calls_current;

    @Column(name = "CALLS_ALL")
    public String calls_all;

    @Column(name = "MEETS_CURRENT")
    public String meets_current;

    @Column(name = "MEETS_ALL")
    public String meets_all;

    @Column(name = "TENDERS_CURRENT")
    public String tenders_current;

    @Column(name = "TENDERS_ALL")
    public String tenders_all;

    @Column(name = "REQUESTS_CURRENT")
    public String requests_current;

    @Column(name = "REQUESTS_ALL")
    public String requests_all;

    @Column(name = "FINANCE_CURRENT")
    public String finance_current;

    @Column(name = "FINANCE_ALL")
    public String finance_all;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
