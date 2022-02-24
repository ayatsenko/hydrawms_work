package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTION_PLAN_TYPE")
public class ActionPlanTypeList {
    @Id
    @Column(name = "ACT_PLAN_TYPE_ID", nullable = false)
    public Long act_plan_type_id;

    @Column(name = "ACT_PLAN_TYPE_NAME", nullable = false)
    public String act_plan_type_name;

    @Column(name = "ACT_PLAN_TYPE_SNAME", nullable = false)
    public String act_plan_type_sname;

    @Column(name = "ACT_PLAN_TYPE_DESCRIPTION", nullable = false)
    public String act_plan_type_description;

    @Column(name = "ACT_PLAN_TYPE_COLOUR", nullable = false)
    public String act_plan_type_colour;
}
