package com.idltd.hydramob.entity.dep_kpi_plans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepartmentUserList {
    @Id
    @Column(name = "DEP_USER_ID", nullable = false)
    public Long dep_user_id;

    @Column(name = "DEP_USER_NAME", nullable = false)
    public String dep_user_name;
}
