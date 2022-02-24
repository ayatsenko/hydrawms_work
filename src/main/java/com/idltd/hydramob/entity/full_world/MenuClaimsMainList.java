package com.idltd.hydramob.entity.full_world;

import javax.persistence.*;

@Entity
public class MenuClaimsMainList {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CLM_ID")
    public Long clm_id;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "WAYS")
    public String ways;

    @Column(name = "LOADS_DATE")
    public String loads_date;

    @Column(name = "UNLOAD_DATE")
    public String unload_date;

    @Column(name = "TRANS_ID")
    public Long trans_id;

    @Column(name = "TRANS_NAME")
    public String trans_name;

    @Column(name = "CARS_ID")
    public Long cars_id;

    @Column(name = "CARS_NUMBER")
    public String cars_number;

    @Column(name = "DRIVER_ID")
    public Long driver_id;

    @Column(name = "DRIVER_NAME")
    public String driver_name;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "TRANS_USER_ID")
    public Long trans_user_id;

    @Column(name = "TRANS_USER_NAME")
    public String trans_user_name;

    @Column(name = "CLM_RATE_CURRENCY_ID")
    public Long clm_rate_currency_id;

    @Column(name = "CLM_RATE_CURRENCY_NAME")
    public String clm_rate_currency_name;

    @Column(name = "CLM_RATE")
    public Float clm_rate;

    @Column(name = "CLM_COSTS_CURRENCY_ID")
    public Long clm_costs_currency_id;

    @Column(name = "CLM_COSTS_CURRENCY_NAME")
    public String clm_costs_currency_name;

    @Column(name = "CLM_COSTS")
    public Float clm_costs;

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
}
