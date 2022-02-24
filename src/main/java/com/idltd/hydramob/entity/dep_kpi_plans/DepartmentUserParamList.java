package com.idltd.hydramob.entity.dep_kpi_plans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepartmentUserParamList {
    @Id
    @Column(name = "USER_KPI_TYPE_ID", nullable = false)
    public Long user_kpi_type_id;

    @Column(name = "USER_KPI_TYPE_NAME", nullable = false)
    public String user_kpi_type_name;
}
