package com.idltd.hydramob.entity.full_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailClaimCostsList {
    @Id
    @Column(name = "CLMCL_ID", nullable = false)
    public Long clmcl_id;

    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLMC_TYPE_ID", nullable = false)
    public Long clmc_type_id;

    @Column(name = "CURRENCY_ID")
    public Long currency_id;

    @Column(name = "CLMCL_SUM")
    public Float clmcl_sum;

    @Column(name = "CLMCL_NOTE")
    public String clmcl_note;

    @Column(name = "CURRENCY_DATE")
    public String currency_date;

    @Column(name = "CLMCL_PERIOD")
    public Long clmcl_period;

    @Column(name = "CLMCL_DAY_COST")
    public Float clmcl_day_cost;
}
