package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class ClientUser_List {
    @Id
    public Long id;

    @Column(name = "USERNAME", nullable = false)
    public String username;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;
}