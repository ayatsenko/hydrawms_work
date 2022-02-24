package com.idltd.hydramob.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailUserList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "USER_PASSWORD")
    public String user_password;

    @Column(name = "USER_TOKEN")
    public String user_token;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "USER_EMAIL")
    public String user_email;

    @Column(name = "USER_MAINPHONE")
    public String user_mainphone;

    @Column(name = "USER_NONMAINPHONE")
    public String user_nonmainphone;

    @Column(name = "USER_STATUS_ID")
    public Long user_status_id;

    @Column(name = "USER_NOTE")
    public String user_note;

    @Column(name = "POS_ID")
    public Long pos_id;

    @Column(name = "TELEGRAM_CHATID")
    public Long telegram_chatid;

    @Column(name = "ROLE_ID")
    public Long role_id;
}
