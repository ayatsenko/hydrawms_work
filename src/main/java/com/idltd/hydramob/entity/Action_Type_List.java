package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Action_Type_List {
    @Id
    public Long act_type_id;

    @Column(name = "ACT_TYPE_NAME")
    public String act_type_name;

    @Column(name = "ACT_TYPE_SNAME")
    public String act_type_sname;

    @Column(name = "ACT_TYPE_DESCRIPTION")
    public String act_type_description;

    @Column(name = "ACT_TYPE_COLOUR")
    public String act_type_colour;

    @Column(name = "ACT_TYPE_SHORT")
    public String act_type_short;
}