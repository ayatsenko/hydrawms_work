package com.idltd.hydramob.entity.users_kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserKPIFactCalls {
    @Id
    @Column(name = "ACT_ID", nullable = false)
    public Long act_id;

    @Column(name = "ACT_PLANE_DATE", nullable = false)
    public String act_plane_date;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "ACT_PLAN_TYPE_SNAME")
    public String act_plan_type_sname;

    @Column(name = "ACT_TYPE_SNAME")
    public String act_type_sname;

    @Column(name = "ACT_STATUS_ID")
    public Long act_status_id;

    @Column(name = "ACT_STATUS_SNAME")
    public String act_status_sname;

    @Column(name = "ACT_STATUS_COLOR")
    public String act_status_color;

    @Column(name = "ACT_RESULT_SNAME")
    public String act_result_sname;

    @Column(name = "ACT_DESCRIPTION")
    public String act_description;
}
