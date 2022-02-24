package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FLDClaimsWaysDetail {
    @Id
    @Column(name = "CNT_WAY_ID")
    public Long cnt_way_id;

    @Column(name = "CNT_WAY_AREA")
    public String cnt_way_area;

    @Column(name = "CNT_WAY_REGION")
    public String cnt_way_region;

    @Column(name = "CNT_WAY_CITY")
    public String cnt_way_city;

    @Column(name = "CNT_WAY_STREET")
    public String cnt_way_street;

    @Column(name = "CNT_WAY_HOME")
    public String cnt_way_home;
}
