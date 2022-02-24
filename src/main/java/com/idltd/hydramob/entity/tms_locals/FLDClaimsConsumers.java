package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FLDClaimsConsumers {
    @Id
    @Column(name = "CNT_WAY_CONS_ID")
    public Long cnt_way_cons_id;

    @Column(name = "CNT_WAY_COMPANY")
    public String cnt_way_company;
}
