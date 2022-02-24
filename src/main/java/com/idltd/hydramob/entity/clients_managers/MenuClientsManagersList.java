package com.idltd.hydramob.entity.clients_managers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientsManagersList {
    @Id
    @Column(name = "CNTSERL_ID", nullable = false)
    public Long cntserl_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CNT_CLASS_ID")
    public Long cnt_class_id;

    @Column(name = "CNT_CLASS_NAME")
    public String cnt_class_name;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "SER_NAME")
    public String ser_name;

    @Column(name = "CNTSERL_DATE")
    public String cntserl_date;

    @Column(name = "CS_USER_LINK_ID")
    public Long cs_user_link_id;

    @Column(name = "CS_USER_ID")
    public Long cs_user_id;

    @Column(name = "CS_USER_NAME")
    public String cs_user_name;

    @Column(name = "CS_START_DATE")
    public String cs_start_date;

    @Column(name = "CS_END_DATE")
    public String cs_end_date;

    @Column(name = "CS_PERSENT")
    public String cs_persent;

    @Column(name = "OPS_USER_LINK_ID")
    public Long ops_user_link_id;

    @Column(name = "OPS_USER_ID")
    public Long ops_user_id;

    @Column(name = "OPS_USER_NAME")
    public String ops_user_name;

    @Column(name = "OPS_PERSENT")
    public String ops_persent;

    @Column(name = "CS_ID")
    public Long cs_id;

    @Column(name = "CS_SNAME")
    public String cs_sname;

    @Column(name = "CS_COLOR")
    public String cs_color;

    @Column(name = "FIN_DATE")
    public String fin_date;

    @Column(name = "LOST_DATE")
    public String lost_date;

    @Column(name = "CNT_SER_STATUS_ID")
    public Long cnt_ser_status_id;

    @Column(name = "CNT_SER_STATUS_SNAME")
    public String cnt_ser_status_sname;

    @Column(name = "CNT_SER_STATUS_COLOR")
    public String cnt_ser_status_color;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "CS_CHECK")
    public Long cs_check;

    @Column(name = "OPS_CHECK")
    public Long ops_check;

    @Column(name = "CNT_CHECK")
    public Long cnt_check;
}
