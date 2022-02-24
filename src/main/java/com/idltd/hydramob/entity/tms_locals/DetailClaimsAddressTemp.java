package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailClaimsAddressTemp {
    @Id
    @Column(name = "CLM_WAY_ID")
    public Long clm_way_id;

    @Column(name = "ORDER_ID")
    public Long order_id;

    @Column(name = "CNT_WAY_DATE")
    public String cnt_way_date;

    @Column(name = "CNT_WAY_ID")
    public Long cnt_way_id;

    @Column(name = "COUNTRY_ID")
    public Long country_id;

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

    @Column(name = "CNT_WAY_CONS_ID")
    public Long cnt_way_cons_id;

    @Column(name = "CNT_WAY_COMPANY")
    public String cnt_way_company;

    @Column(name = "CNT_WAY_CONTACT_ID")
    public Long cnt_way_contact_id;

    @Column(name = "CNT_WAY_CONTACT_PERSON")
    public String cnt_way_contact_person;

    @Column(name = "CNT_WAY_CONTACT_PHONE")
    public String cnt_way_contact_phone;

    @Column(name = "CNT_WAY_CONTACT_PHONE2")
    public String cnt_way_contact_phone2;
}
