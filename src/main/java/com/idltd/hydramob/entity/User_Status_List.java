package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User_Status_List {
    @Id
    public Long user_status_id;

    @Column(name = "USER_STATUS_NAME", nullable = false)
    public String user_status_name;

    @Column(name = "USER_STATUS_COLOUR", nullable = false)
    public String user_status_colour;
}