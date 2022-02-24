package com.idltd.hydramob.entity.departments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailDepartmentsList {
    @Id
    @Column(name = "DEP_ID", nullable = false)
    public Long dep_id;

    @Column(name = "DEP_NAME")
    public String dep_name;

    @Column(name = "DEP_SNAME")
    public String dep_sname;

    @Column(name = "DEP_DESCRIPTION")
    public String dep_description;

    @Column(name = "DEP_COLOUR")
    public String dep_colour;
}
