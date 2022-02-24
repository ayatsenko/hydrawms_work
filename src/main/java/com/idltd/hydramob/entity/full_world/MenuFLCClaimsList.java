package com.idltd.hydramob.entity.full_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLCClaimsList {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLM_DATE")
    public String clm_date;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "SER_SNAME")
    public String ser_sname;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "CLM_SUM_CURRENCY_NAME")
    public String clm_sum_currency_name;

    @Column(name = "CLM_SUM")
    public Float clm_sum;

    @Column(name = "CLM_RATE_CURRENCY_NAME")
    public String clm_rate_currency_name;

    @Column(name = "CLM_RATE")
    public Float clm_rate;

    @Column(name = "CLM_COSTS")
    public Float clm_costs;

    @Column(name = "CLM_PROFIT")
    public Float clm_profit;

    @Column(name = "CLM_AIM_TYPE_SNAME")
    public String clm_aim_type_sname;

    @Column(name = "CLM_STATUS_ID")
    public Long clm_status_id;

    @Column(name = "CLM_STATUS_SNAME")
    public String clm_status_sname;

    @Column(name = "CLM_STATUS_COLOUR")
    public String clm_status_colour;

    @Column(name = "CLM_CHECK")
    public Long clm_check;

    @Column(name = "CLM_UNCHECK")
    public Long clm_uncheck;

    @Column(name = "TRANS_ID")
    public Long trans_id;

    @Column(name = "TRANS_NAME")
    public String trans_name;

    @Column(name = "CAR_NUMBER")
    public String car_number;

    @Column(name = "CLM_WAY_DATE")
    public String clm_way_date;
}
