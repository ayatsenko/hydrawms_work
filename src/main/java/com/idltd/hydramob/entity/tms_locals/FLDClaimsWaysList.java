package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FLDClaimsWaysList {
    @Id
    @Column(name = "CNT_WAY_ID")
    public Long cnt_way_id;

    @Column(name = "CNT_WAY_NAME")
    public String cnt_way_name;
}
