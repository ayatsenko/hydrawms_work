package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientsList {
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

    @Column(name = "CNT_SOURCE_ID")
    public Long cnt_source_id;

    @Column(name = "CNT_SOURCE_NAME")
    public String cnt_source_name;
}
