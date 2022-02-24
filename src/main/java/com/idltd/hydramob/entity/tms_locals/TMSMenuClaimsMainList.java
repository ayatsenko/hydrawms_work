package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.*;

@Entity
public class TMSMenuClaimsMainList {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CLM_ID")
    public Long clm_id;

    @Column(name = "CLM_DATE")
    public String clm_date;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CLM_WAY_DATE")
    public String clm_way_date;

    @Column(name = "LOAD_POINT")
    public String load_point;

    @Column(name = "UNLOAD_POINT")
    public String unload_point;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "DOC_NUMBER")
    public String doc_number;

    @Column(name = "LOADS_DETAIL")
    public String loads_detail;

    @Column(name = "CLM_COSTS_CURRENCY_ID")
    public Long clm_costs_currency_id;

    @Column(name = "CLM_COSTS_CURRENCY_NAME")
    public String clm_costs_currency_name;

    @Column(name = "CLM_SUM")
    public Float clm_sum;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "TRANS_ID")
    public Long trans_id;

    @Column(name = "TRANS_NAME")
    public String trans_name;

    @Column(name = "TRANS_DETAIL")
    public String trans_detail;

    @Column(name = "CLM_RATE_CURRENCY_ID")
    public Long clm_rate_currency_id;

    @Column(name = "CLM_RATE_CURRENCY_NAME")
    public String clm_rate_currency_name;

    @Column(name = "CLM_RATE")
    public Float clm_rate;

    @Column(name = "CLM_COSTS")
    public Float clm_costs;

    @Column(name = "TRANS_USER_ID")
    public Long trans_user_id;

    @Column(name = "TRANS_USER_NAME")
    public String trans_user_name;

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

    @Column(name = "CLM_STATUS_NEXT_ID")
    public Long clm_status_next_id;

    @Column(name = "CLM_PROFIT")
    public String clm_profit;
}
