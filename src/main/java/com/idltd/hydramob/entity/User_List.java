package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User_List {
    @Id
    public Long id;

    @Column(name = "USERNAME", nullable = false)
    public String username;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "SUPPORT_ID", nullable = false)
    public Long support_id;
}