package com.idltd.hydramob.entity.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Roles {
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    public Long role_id;

    @Column(name = "ROLE_NAME", nullable = false)
    public String role_name;

    @Column(name = "ROLE_SNAME")
    public String role_sname;

    @Column(name = "ROLE_DESCRIPTION")
    public String role_description;
}
