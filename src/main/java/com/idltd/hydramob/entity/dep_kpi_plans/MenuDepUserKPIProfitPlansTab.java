package com.idltd.hydramob.entity.dep_kpi_plans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDepUserKPIProfitPlansTab {
    @Id
    @Column(name = "KPIRDPCL_ID", nullable = false)
    public Long kpirdpcl_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "KPIRDPCL_PLAN")
    public String kpirdpcl_plan;
}
