package com.idltd.hydramob.entity.user_settings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailUserSettings {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "POS_ID")
    public Long pos_id;

    @Column(name = "POS_NAME")
    public String pos_name;

    @Column(name = "ROLE_ID")
    public Long role_id;

    @Column(name = "ROLE_NAME")
    public String role_name;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "USER_EMAIL")
    public String user_email;

    @Column(name = "USER_MAINPHONE")
    public String user_mainphone;

    @Column(name = "USER_NONMAINPHONE")
    public String user_nonmainphone;

    @Column(name = "TELEGRAM_CHATID")
    public String telegram_chatid;
}
