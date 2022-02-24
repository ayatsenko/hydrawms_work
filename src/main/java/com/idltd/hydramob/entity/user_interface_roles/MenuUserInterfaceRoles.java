package com.idltd.hydramob.entity.user_interface_roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserInterfaceRoles {
    @Id
    @Column(name = "UR_ID", nullable = false)
    public Long ur_id;

    @Column(name = "ROLE_ID", nullable = false)
    public Long role_id;

    @Column(name = "ROLE_NAME", nullable = false)
    public String role_name;
}
