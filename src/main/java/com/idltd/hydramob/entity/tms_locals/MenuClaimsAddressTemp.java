package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimsAddressTemp {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CNT_WAY_DATE")
    public String cnt_way_date;

    @Column(name = "CLM_WAY_ID")
    public Long clm_way_id;

    @Column(name = "ORDER_ID")
    public Long order_id;

    @Column(name = "CLM_WAY_SNAME")
    public String clm_way_sname;

    @Column(name = "CNT_WAY_NAME")
    public String cnt_way_name;

    @Column(name = "CNT_WAY_COMPANY")
    public String cnt_way_company;

    @Column(name = "CNT_WAY_CONTACT_PERSON")
    public String cnt_way_contact_person;
}
