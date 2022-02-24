package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_KPI_TYPE")
public class Users_KPI_Type_List {
    @Id
    public Long user_kpi_type_id;

    @Column(name = "USER_KPI_TYPE_NAME", nullable = false)
    public String user_kpi_type_name;

    @Column(name = "USER_KPI_TYPE_SNAME", nullable = false)
    public String user_kpi_type_sname;

    @Column(name = "USER_KPI_TYPE_DESCRIPTION", nullable = false)
    public String user_kpi_type_description;

    @Column(name = "USER_KPI_TYPE_COLOUR", nullable = false)
    public String user_kpi_type_colour;
}