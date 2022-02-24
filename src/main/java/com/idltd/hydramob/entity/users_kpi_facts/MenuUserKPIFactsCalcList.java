package com.idltd.hydramob.entity.users_kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserKPIFactsCalcList {
    @Id
    @Column(name = "USER_KPI_TYPE_ID", nullable = false)
    public Long user_kpi_type_id;

    @Column(name = "USER_KPI_TYPE_NAME")
    public String user_kpi_type_name;

    @Column(name = "KPIRUPL_PLAN")
    public String kpirupl_plan;

    @Column(name = "KPIRUPL_FACT")
    public String kpirupl_fact;

    @Column(name = "KPIRUPL_PERSENT")
    public String kpirupl_persent;

    @Column(name = "KPIRUPL_MALUS")
    public String kpirupl_malus;

    @Column(name = "KPIRUPL_COLOUR")
    public String kpirupl_colour;
}
