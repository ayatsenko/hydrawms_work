package com.idltd.hydramob.entity.departments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDepartmentsList {
    @Id
    @Column(name = "DEP_ID", nullable = false)
    public Long dep_id;

    @Column(name = "DEP_NAME")
    public String dep_name;

    @Column(name = "DEP_SNAME")
    public String dep_sname;
}
