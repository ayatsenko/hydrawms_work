package com.idltd.hydramob.entity.users_kpi_meets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_KPI_MEETINGS_TYPE")
public class UserKPIMeetTypesList {
    @Id
    @Column(name = "USER_KPI_MEET_TYPE_ID", nullable = false)
    public Long user_kpi_meet_type_id;

    @Column(name = "USER_KPI_MEET_TYPE_NAME")
    public String user_kpi_meet_type_name;

    @Column(name = "USER_KPI_MEET_TYPE_SNAME")
    public String user_kpi_meet_type_sname;

    @Column(name = "USER_KPI_MEET_TYPE_DESCRIPTION")
    public String user_kpi_meet_type_description;

    @Column(name = "USER_KPI_MEET_TYPE_COLOUR")
    public String user_kpi_meet_type_colour;
}
