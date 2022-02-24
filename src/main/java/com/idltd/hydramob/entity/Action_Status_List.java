package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Action_Status_List {
    @Id
    public Long act_status_id;

    @Column(name = "ACT_STATUS_NAME")
    public String act_status_name;

    @Column(name = "ACT_STATUS_SNAME")
    public String act_status_sname;

    @Column(name = "ACT_STATUS_DESCRIPTION")
    public String act_status_description;

    @Column(name = "ACT_STATUS_COLOUR")
    public String act_status_colour;
}