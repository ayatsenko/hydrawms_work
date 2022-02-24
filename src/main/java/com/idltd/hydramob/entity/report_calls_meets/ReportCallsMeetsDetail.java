package com.idltd.hydramob.entity.report_calls_meets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportCallsMeetsDetail {
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

    @Column(name = "ACT_TYPE_ID", nullable = false)
    public Long act_type_id;

    @Column(name = "ACT_TYPE_SNAME", nullable = false)
    public String act_type_sname;

    @Column(name = "ACT_STATUS_ID", nullable = false)
    public Long act_status_id;

    @Column(name = "ACT_STATUS_SNAME", nullable = false)
    public String act_status_sname;

    @Column(name = "ACT_RESULT_ID", nullable = false)
    public Long act_result_id;

    @Column(name = "ACT_RESULT_SNAME", nullable = false)
    public String act_result_sname;

    @Column(name = "ACT_RESULT_COLOUR", nullable = false)
    public String act_result_colour;

    @Column(name = "ACT_DESCRIPTION", nullable = false)
    public String act_description;
}
