package com.idltd.hydramob.entity.kpi_facts.cs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIFactsCsClientsResultDebtors {
    @Id
    @Column(name = "RN_ID", nullable = false)
    public Long rn_id;

    @Column(name = "FIN_MONTH")
    public Long fin_month;

    @Column(name = "FIN_MONTH_NAME")
    public String fin_month_name;

    @Column(name = "FIN_SUM")
    public String fin_sum;

    @Column(name = "KPIRUPL_PLAN")
    public String kpirupl_plan;

    @Column(name = "KPIRUPL_FACT")
    public String kpirupl_fact;

    @Column(name = "KPIRUPL_PERSENT")
    public String kpirupl_persent;

    @Column(name = "KPIRUPL_PAID")
    public Long kpirupl_paid;

    @Column(name = "RESULT")
    public String result;

    @Column(name = "RESULT_SUM")
    public String result_sum;

    @Column(name = "RESULT_COLOR")
    public String result_color;
}
