package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrentUserDetail {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "ROLE_ID", nullable = false)
    public Long role_id;

    @Column(name = "ROLE_NAME", nullable = false)
    public String role_name;
}