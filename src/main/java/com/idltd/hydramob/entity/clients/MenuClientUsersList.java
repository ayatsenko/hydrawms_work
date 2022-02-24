package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientUsersList {
    @Id
    @Column(name = "CNTUL_ID", nullable = false)
    public Long cntul_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "CNTUL_MAIN_ID")
    public Long cntul_main_id;
}
