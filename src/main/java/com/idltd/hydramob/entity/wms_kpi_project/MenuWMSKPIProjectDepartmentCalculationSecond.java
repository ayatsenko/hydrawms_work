package com.idltd.hydramob.entity.wms_kpi_project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSKPIProjectDepartmentCalculationSecond {
    @Id
    @Column(name = "WMS_KPI_RESULT_ID", nullable = false)
    public Long wms_kpi_result_id;

    @Column(name = "WMS_DEP_ID", nullable = false)
    public Long wms_dep_id;

    @Column(name = "WMS_PROJECT_ID")
    public Long wms_project_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "WMS_KPI_YEAR_ID")
    public Long wms_kpi_year_id;

    @Column(name = "WMS_KPI_MONTH_ID")
    public Long wms_kpi_month_id;

    @Column(name = "WMS_KPI_PARAM_ID")
    public Long wms_kpi_param_id;

    @Column(name = "WMS_KPI_PARAM_NAME")
    public String wms_kpi_param_name;

    @Column(name = "WMS_KPI_PLAN")
    public String wms_kpi_plan;

    @Column(name = "WMS_KPI_FACT")
    public String wms_kpi_fact;

    @Column(name = "WMS_KPI_MALUS")
    public String wms_kpi_malus;

    @Column(name = "WMS_KPI_FINANCE")
    public String wms_kpi_finance;

    @Column(name = "WMS_KPI_VIEW_TYPE_ID")
    public Long wms_kpi_view_type_id;

    @Column(name = "WMS_KPI_PLAN_UNIT")
    public String wms_kpi_plan_unit;

    @Column(name = "WMS_KPI_FACT_UNIT")
    public String wms_kpi_fact_unit;

    @Column(name = "WMS_KPI_PERCENT")
    public String wms_kpi_percent;
}
