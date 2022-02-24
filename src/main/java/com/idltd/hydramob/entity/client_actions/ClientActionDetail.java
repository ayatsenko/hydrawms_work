package com.idltd.hydramob.entity.client_actions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientActionDetail {
    @Id
    @Column(name = "ACT_ID", nullable = false)
    public Long act_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "ACT_DATE")
    public String act_date;

    @Column(name = "ACT_TYPE_ID")
    public Long act_type_id;

    @Column(name = "ACT_RESULT_ID")
    public Long act_result_id;

    @Column(name = "ACT_DESCRIPTION")
    public String act_description;

    @Column(name = "ACT_STATUS_ID")
    public Long act_status_id;

    @Column(name = "ACT_PLANE_DATE")
    public String act_plane_date;

    @Column(name = "ACT_FACT_DATE")
    public String act_fact_date;

    @Column(name = "ACT_PLAN_TYPE_ID")
    public Long act_plan_type_id;

    @Column(name = "ACT_END_DATE")
    public String act_end_date;

    @Column(name = "COLD_CNT_NAME")
    public String cold_cnt_name;

    @Column(name = "COLD_CONTACT")
    public String cold_contact;

    @Column(name = "COLD_PHONE")
    public String cold_phone;

    @Column(name = "ACT_ACT_TYPE_ID")
    public Long act_act_type_id;
}
