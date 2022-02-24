package com.idltd.hydramob.entity.part_world_old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailPartWorldClaimsList {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLM_DATE")
    public String clm_date;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "CLM_SUM")
    public Float clm_sum;

    @Column(name = "CLM_SUM_CURRENCY_ID")
    public Long clm_sum_currency_id;

    @Column(name = "CLM_SUM_CURRENCY_DATE")
    public String clm_sum_currency_date;

    @Column(name = "CLM_RATE")
    public Float clm_rate;

    @Column(name = "CLM_RATE_CURRENCY_ID")
    public Long clm_rate_currency_id;

    @Column(name = "CLM_RATE_CURRENCY_DATE")
    public String clm_rate_currency_date;

    @Column(name = "CLM_COSTS")
    public Float clm_costs;

    @Column(name = "CLM_PROFIT")
    public Float clm_profit;

    @Column(name = "CLM_AIM_TYPE_ID")
    public Long clm_aim_type_id;

    @Column(name = "CLM_STATUS_ID")
    public Long clm_status_id;

    @Column(name = "CLM_NOTE")
    public String clm_note;
}
