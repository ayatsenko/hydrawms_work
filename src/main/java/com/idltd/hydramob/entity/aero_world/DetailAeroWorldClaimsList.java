package com.idltd.hydramob.entity.aero_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailAeroWorldClaimsList {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CLM_CLIENT_NUM")
    public String clm_client_num;

    @Column(name = "LATITUDE")
    public String latitude;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "TRANS_ID")
    public Long trans_id;

    @Column(name = "START_COUNTRY_ID")
    public Long start_country_id;

    @Column(name = "START_AIRPORT_ID")
    public Long start_airport_id;

    @Column(name = "END_COUNTRY_ID")
    public Long end_country_id;

    @Column(name = "END_AIRPORT_ID")
    public Long end_airport_id;

    @Column(name = "MAWB")
    public String mawb;

    @Column(name = "HAWB")
    public String hawb;

    @Column(name = "SHIPPER_ID")
    public Long shipper_id;

    @Column(name = "CONSIGNEE_ID")
    public Long consignee_id;

    @Column(name = "PACKAGE_COUNT")
    public String package_count;

    @Column(name = "GROSS_WEIGHT")
    public String gross_weight;

    @Column(name = "CHARGEBLE_WEIGHT")
    public String chargeble_weight;

    @Column(name = "CLM_SUM")
    public String clm_sum;

    @Column(name = "CLM_SUM_CURRENCY_ID")
    public Long clm_sum_currency_id;
}
