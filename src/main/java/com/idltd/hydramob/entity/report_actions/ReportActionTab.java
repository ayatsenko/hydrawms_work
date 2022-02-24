package com.idltd.hydramob.entity.report_actions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportActionTab {
    @Id
    public Long act_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USERNAME", nullable = false)
    public String username;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "ACT_DATE", nullable = false)
    public String act_date;

    @Column(name = "ACT_PLANE_DATE", nullable = false)
    public String act_plane_date;

    @Column(name = "ACT_END_DATE", nullable = false)
    public String act_end_date;

    @Column(name = "ACT_FACT_DATE", nullable = false)
    public String act_fact_date;

    @Column(name = "ACT_TYPE_ID", nullable = false)
    public Long act_type_id;

    @Column(name = "ACT_TYPE_SNAME", nullable = false)
    public String act_type_sname;

    @Column(name = "ACT_TYPE_SHORT", nullable = false)
    public String act_type_short;

    @Column(name = "ACT_STATUS_ID", nullable = false)
    public Long act_status_id;

    @Column(name = "ACT_STATUS_SNAME", nullable = false)
    public String act_status_sname;

    @Column(name = "ACT_STATUS_COLOUR", nullable = false)
    public String act_status_colour;

    @Column(name = "ACT_RESULT_ID", nullable = false)
    public Long act_result_id;

    @Column(name = "ACT_RESULT_SNAME", nullable = false)
    public String act_result_sname;

    @Column(name = "ACT_RESULT_SHORT", nullable = false)
    public String act_result_short;

    @Column(name = "ACT_RESULT_COLOUR", nullable = false)
    public String act_result_colour;

    @Column(name = "ACT_DESCRIPTION", nullable = false)
    public String act_description;

    @Column(name = "ACT_PLAN_TYPE_ID", nullable = false)
    public Long act_plan_type_id;

    @Column(name = "ACT_PLAN_TYPE_SNAME", nullable = false)
    public String act_plan_type_sname;

    @Column(name = "ACT_PLAN_TYPE_COLOUR", nullable = false)
    public String act_plan_type_colour;

    @Column(name = "ACT_DRAW", nullable = false)
    public Long act_draw;

    @Column(name = "ACT_ACT_TYPE_ID", nullable = false)
    public Long act_act_type_id;

    @Column(name = "ACT_ACT_TYPE_SNAME", nullable = false)
    public String act_act_type_sname;

    @Column(name = "ACT_ACT_TYPE_COLOUR", nullable = false)
    public String act_act_type_colour;

    @Column(name = "COLD_CONTACT")
    public String cold_contact;

    @Column(name = "COLD_PHONE")
    public String cold_phone;
}
