package com.idltd.hydramob.entity.full_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimWaysTabList {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CLMWL_ID", nullable = false)
    public Long clmwl_id;

    @Column(name = "CLM_WAY_DATE")
    public String clm_way_date;

    @Column(name = "CLM_WAY_FACT_DATE")
    public String clm_way_fact_date;

    @Column(name = "CLM_ZIP_CODE")
    public String clm_zip_code;

    @Column(name = "COUNTRY_NAME")
    public String country_name;

    @Column(name = "CLM_WAY_CITY")
    public String clm_way_city;

    @Column(name = "CLM_WAY_STREET")
    public String clm_way_street;

    @Column(name = "CLM_WAY_HOME")
    public String clm_way_home;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
