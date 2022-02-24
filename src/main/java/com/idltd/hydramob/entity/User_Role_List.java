package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_ROLES")
public class User_Role_List {
    @Id
    public Long user_id;

    @Column(name = "role_id", nullable = false)
    public Long role_id;
}