package com.idltd.hydramob.entity.action_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActionType {
    @Id
    @Column(name = "ACT_TYPE_ID", nullable = false)
    public Long act_type_id;

    @Column(name = "ACT_TYPE_SNAME", nullable = false)
    public String act_type_sname;

    @Column(name = "ACT_TYPE_NAME")
    public String act_type_name;

    @Column(name = "ACT_TYPE_DESCRIPTION")
    public String act_type_description;

    @Column(name = "ACT_TYPE_COLOUR")
    public String act_type_colour;

    @Column(name = "ACT_TYPE_SHORT")
    public String act_type_short;
}
