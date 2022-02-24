package com.idltd.hydramob.entity.departments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDepartmentUsersList {
    @Id
    @Column(name = "DEP_USER_LINK_ID", nullable = false)
    public Long dep_user_link_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;
}
