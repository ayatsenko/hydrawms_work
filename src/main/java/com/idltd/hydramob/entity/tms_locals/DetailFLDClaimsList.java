package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailFLDClaimsList {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLM_DATE")
    public String clm_date;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "CLM_SUM")
    public String clm_sum;

    @Column(name = "CLM_SUM_CURRENCY_ID")
    public Long clm_sum_currency_id;

    @Column(name = "CLM_SUM_CURRENCY_DATE")
    public String clm_sum_currency_date;

    @Column(name = "CLM_RATE")
    public String clm_rate;

    @Column(name = "CLM_RATE_CURRENCY_ID")
    public Long clm_rate_currency_id;

    @Column(name = "CLM_RATE_CURRENCY_DATE")
    public String clm_rate_currency_date;

    @Column(name = "CLM_COSTS")
    public String clm_costs;

    @Column(name = "CLM_PROFIT")
    public String clm_profit;

    @Column(name = "CLM_AIM_TYPE_ID")
    public Long clm_aim_type_id;

    @Column(name = "CLM_STATUS_ID")
    public Long clm_status_id;

    @Column(name = "CLM_NOTE")
    public String clm_note;

    @Column(name = "CLM_WAY_DATE")
    public String clm_way_date;

    @Column(name = "CLM_CLIENT_NUM")
    public String clm_client_num;

    @Column(name = "TRANS_ID")
    public Long trans_id;
}
