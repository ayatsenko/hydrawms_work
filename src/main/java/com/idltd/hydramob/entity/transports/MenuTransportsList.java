package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTransportsList {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "TMS_CONTT_DATE")
    public String tms_contt_date;

    @Column(name = "TMS_CONTT_COLOR")
    public String tms_contt_color;

    @Column(name = "TMS_LIC_DATE")
    public String tms_lic_date;

    @Column(name = "TMS_LIC_COLOR")
    public String tms_lic_color;

    @Column(name = "TMS_INS_DATE")
    public String tms_ins_date;

    @Column(name = "TMS_INS_COLOR")
    public String tms_ins_color;

    @Column(name = "CS_SNAME")
    public String cs_sname;

    @Column(name = "CS_COLOR")
    public String cs_color;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "CNT_USER_ACCESS")
    public Long cnt_user_access;

    @Column(name = "CNT_USER_SHOW")
    public Long cnt_user_show;
}
