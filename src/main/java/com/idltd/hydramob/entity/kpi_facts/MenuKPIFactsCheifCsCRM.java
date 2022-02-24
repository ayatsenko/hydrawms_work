package com.idltd.hydramob.entity.kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIFactsCheifCsCRM {
    @Id
    @Column(name = "USER_KPI_CRM_TYPE_ID", nullable = false)
    public Long user_kpi_crm_type_id;

    @Column(name = "USER_KPI_CRM_TYPE_SNAME")
    public String user_kpi_crm_type_sname;

    @Column(name = "USER_KPI_CRM_TYPE_FACT")
    public String user_kpi_crm_type_fact;

    @Column(name = "USER_KPI_CRM_TYPE_COLOUR")
    public String user_kpi_crm_type_colour;
}
