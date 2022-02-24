package com.idltd.hydramob.entity.kpi_roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIRoleMain {
    @Id
    @Column(name = "KPI_ROLE_ID", nullable = false)
    public Long kpi_role_id;

    @Column(name = "KPI_ROLE_NAME")
    public String kpi_role_name;

    @Column(name = "KPI_ROLE_SNAME")
    public String kpi_role_sname;

    @Column(name = "KPI_ROLE_DESCRIPTION")
    public String kpi_role_description;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
