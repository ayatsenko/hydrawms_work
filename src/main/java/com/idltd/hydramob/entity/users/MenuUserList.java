package com.idltd.hydramob.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "ROLE_ID")
    public Long role_id;

    @Column(name = "ROLE_NAME")
    public String role_name;

    @Column(name = "USER_STATUS_NAME")
    public String user_status_name;

    @Column(name = "USER_STATUS_COLOUR")
    public String user_status_colour;
}
