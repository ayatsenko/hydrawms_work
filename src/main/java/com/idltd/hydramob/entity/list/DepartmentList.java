package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepartmentList {
    @Id
    @Column(name = "DEP_ID", nullable = false)
    public Long dep_id;

    @Column(name = "DEP_NAME", nullable = false)
    public String dep_name;
}
