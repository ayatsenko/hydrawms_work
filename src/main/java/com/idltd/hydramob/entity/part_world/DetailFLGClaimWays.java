package com.idltd.hydramob.entity.part_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailFLGClaimWays {
    @Id
    @Column(name = "CLMWL_ID", nullable = false)
    public Long clmwl_id;

    @Column(name = "CLM_ID")
    public Long clm_id;

    @Column(name = "CLM_WAY_ID")
    public Long clm_way_id;

    @Column(name = "COUNTRY_ID")
    public Long country_id;

    @Column(name = "CLM_WAY_CITY")
    public String clm_way_city;

    @Column(name = "CLM_WAY_STREET")
    public String clm_way_street;

    @Column(name = "CLM_WAY_HOME")
    public String clm_way_home;

    @Column(name = "CLM_WAY_DATE")
    public String clm_way_date;

    @Column(name = "CLM_WAY_FACT_DATE")
    public String clm_way_fact_date;

    @Column(name = "CLM_WAY_COMPANY")
    public String clm_way_company;

    @Column(name = "CLM_ZIP_CODE")
    public String clm_zip_code;

    @Column(name = "ORDER_ID")
    public Long order_id;
}
