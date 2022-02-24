package com.idltd.hydramob.entity.part_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLGClaimWHParamList {
    @Id
    @Column(name = "CLMWHPL_ID", nullable = false)
    public Long clmwhpl_id;

    @Column(name = "WH_PARAM_ID", nullable = false)
    public Long wh_param_id;

    @Column(name = "WH_PARAM_NAME", nullable = false)
    public String wh_param_name;

    @Column(name = "CLMWHL_CURRENCY_ID")
    public Long clmwhl_currency_id;

    @Column(name = "CURRENCY_NAME")
    public String currency_name;

    @Column(name = "CLMWHL_WH_PARAM_RATE")
    public String clmwhl_wh_param_rate;

    @Column(name = "CLMWHL_WH_DAY_COUNT")
    public String clmwhl_wh_day_count;

    @Column(name = "CLMWHL_WH_PARAM_RESULT")
    public String clmwhl_wh_param_result;
}
