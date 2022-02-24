package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSeaShipsClaimsList {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "LOAD_DATE")
    public String load_date;

    @Column(name = "UNLOAD_DATE")
    public String unload_date;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "LOAD_COUNTRY_ID")
    public Long load_country_id;

    @Column(name = "LOAD_CODE")
    public String load_code;

    @Column(name = "LOAD_SHIPYARD")
    public String load_shipyard;

    @Column(name = "UNLOAD_COUNTRY_ID")
    public Long unload_country_id;

    @Column(name = "LOAD_POINT")
    public String load_point;

    @Column(name = "UNLOAD_CODE")
    public String unload_code;

    @Column(name = "UNLOAD_SHIPYARD")
    public String unload_shipyard;

    @Column(name = "UNLOAD_POINT")
    public String unload_point;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "CLM_SUM")
    public String clm_sum;

    @Column(name = "CLM_PROFIT")
    public String clm_profit;

    @Column(name = "CLM_SH_TYPE_ID")
    public Long clm_sh_type_id;

    @Column(name = "CLM_SH_TYPE_NAME")
    public String clm_sh_type_name;

    @Column(name = "CLM_STATUS_ID")
    public Long clm_status_id;

    @Column(name = "CLM_STATUS_SNAME")
    public String clm_status_sname;

    @Column(name = "CLM_STATUS_COLOUR")
    public String clm_status_colour;

    @Column(name = "CLM_COSTS")
    public String clm_costs;

    @Column(name = "CLM_PROFIT_COLOR")
    public String clm_profit_color;

    @Column(name = "CLM_CONTAINERS_COUNT")
    public Long clm_containers_count;

    @Column(name = "CLM_CONTAINERS_WEIGHT")
    public String clm_containers_weight;
}
