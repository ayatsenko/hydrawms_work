package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users_List {
    @Id
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "USER_EMAIL")
    public String user_email;

    @Column(name = "USER_MAINPHONE")
    public String user_mainphone;

    @Column(name = "USER_NONMAINPHONE")
    public String user_nonmainphone;

    @Column(name = "POS_ID")
    public Long pos_id;
}