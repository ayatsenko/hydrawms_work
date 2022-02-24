package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Action_Result_List {
    @Id
    public Long act_result_id;

    @Column(name = "ACT_RESULT_NAME")
    public String act_result_name;

    @Column(name = "ACT_RESULT_SNAME")
    public String act_result_sname;

    @Column(name = "ACT_RESULT_DESCRIPTION")
    public String act_result_description;

    @Column(name = "ACT_RESULT_COLOUR")
    public String act_result_colour;

    @Column(name = "ACT_RESULT_SHORT")
    public String act_result_short;
}