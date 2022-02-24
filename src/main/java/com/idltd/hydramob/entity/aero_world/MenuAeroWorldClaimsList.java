package com.idltd.hydramob.entity.aero_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuAeroWorldClaimsList {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "TRANS_ID")
    public Long trans_id;

    @Column(name = "TRANS_NAME")
    public String trans_name;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "ROUTING")
    public String routing;

    @Column(name = "MAWB")
    public String mawb;

    @Column(name = "HAWB")
    public String hawb;

    @Column(name = "SHIPPER_ID")
    public Long shipper_id;

    @Column(name = "SHIPPER_NAME")
    public String shipper_name;

    @Column(name = "CONSIGNEE")
    public Long consignee;

    @Column(name = "CONSIGNEE_NAME")
    public String consignee_name;

    @Column(name = "PACKAGE_COUNT")
    public String package_count;

    @Column(name = "GROSS_WEIGHT")
    public String gross_weight;

    @Column(name = "CHARGEBLE_WEIGHT")
    public String chargeble_weight;

    @Column(name = "ETD")
    public String etd;

    @Column(name = "CLM_STATUS_ID")
    public Long clm_status_id;

    @Column(name = "CLM_STATUS_SNAME")
    public String clm_status_sname;

    @Column(name = "CLM_STATUS_COLOUR")
    public String clm_status_colour;

    @Column(name = "CLM_SUM")
    public String clm_sum;

    @Column(name = "CLM_COSTS")
    public String clm_costs;

    @Column(name = "CLM_PROFIT")
    public String clm_profit;

    @Column(name = "CLM_PROFIT_COLOR")
    public String clm_profit_color;
}
