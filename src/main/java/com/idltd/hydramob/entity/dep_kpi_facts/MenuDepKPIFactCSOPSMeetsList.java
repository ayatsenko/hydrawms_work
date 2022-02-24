package com.idltd.hydramob.entity.dep_kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDepKPIFactCSOPSMeetsList {
    @Id
    @Column(name = "USER_KPI_MEET_ID", nullable = false)
    public Long user_kpi_meet_id;

    @Column(name = "USER_KPI_MEET_NAME")
    public String user_kpi_meet_name;

    @Column(name = "USER_KPI_DATE")
    public String user_kpi_date;

    @Column(name = "USER_KPI_MEET_TYPE_ID")
    public Long user_kpi_meet_type_id;

    @Column(name = "USER_KPI_MEET_TYPE_NAME")
    public String user_kpi_meet_type_name;

    @Column(name = "USER_KPI_ADD_DATE")
    public String user_kpi_add_date;

    @Column(name = "USER_KPI_ADD_USER_ID")
    public Long user_kpi_add_user_id;

    @Column(name = "USER_KPI_ADD_USER_NAME")
    public String user_kpi_add_user_name;

    @Column(name = "BLOCK_CHECK")
    public Long block_check;
}
