package com.idltd.hydramob.entity.dep_kpi_plans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepartmentParamList {
    @Id
    @Column(name = "DEP_KPI_TYPE_ID", nullable = false)
    public Long dep_kpi_type_id;

    @Column(name = "DEP_KPI_TYPE_NAME", nullable = false)
    public String dep_kpi_type_name;
}
