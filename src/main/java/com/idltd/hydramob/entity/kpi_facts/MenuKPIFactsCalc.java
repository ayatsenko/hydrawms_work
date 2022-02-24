package com.idltd.hydramob.entity.kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIFactsCalc {
    @Column(name = "RN_ID", nullable = false)
    public Long rn_id;

    @Column(name = "KPIRUPL_ID", nullable = false)
    public Long kpirupl_id;

    @Id
    @Column(name = "USER_KPI_TYPE_ID", nullable = false)
    public Long user_kpi_type_id;

    @Column(name = "USER_KPI_TYPE_NAME")
    public String user_kpi_type_name;

    @Column(name = "USER_KPI_TYPE_PLAN_ID")
    public Long user_kpi_type_plan_id;

    @Column(name = "USER_KPI_TYPE_MALUS_ID")
    public Long user_kpi_type_malus_id;

    @Column(name = "USER_KPI_TYPE_BONUS_ID")
    public Long user_kpi_type_bonus_id;

    @Column(name = "USER_KPI_TYPE_GENERAL_ID")
    public Long user_kpi_type_general_id;

    @Column(name = "KPIRUPL_PLAN")
    public String kpirupl_plan;

    @Column(name = "KPIRUPL_FACT")
    public String kpirupl_fact;

    @Column(name = "KPIRUPL_PERSENT")
    public String kpirupl_persent;

    @Column(name = "KPIRUPL_MALUS")
    public String kpirupl_malus;

    @Column(name = "KPIRUPL_FINANCE")
    public String kpirupl_finance;

    @Column(name = "KPIRUPL_COLOUR")
    public String kpirupl_colour;
}
