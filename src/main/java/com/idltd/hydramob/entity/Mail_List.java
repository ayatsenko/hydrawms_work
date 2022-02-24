package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEND_MAIL")
public class Mail_List {
    @Id
    public Long user_id;

    @Column(name = "USER_MAIL", nullable = false)
    public String user_mail;

    @Column(name = "MAIL_SUBJECT", nullable = false)
    public String mail_subject;

    @Column(name = "MAIL_TEXT", nullable = false)
    public String mail_text;
}
