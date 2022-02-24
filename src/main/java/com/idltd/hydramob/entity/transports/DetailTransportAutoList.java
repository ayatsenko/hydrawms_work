package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTransportAutoList {
    @Id
    @Column(name = "CNTC_ID", nullable = false)
    public Long cntc_id;

    @Column(name = "CNTC_TYPE_ID")
    public Long cntc_type_id;

    @Column(name = "CNTC_TYPE_BRAND")
    public String cntc_type_brand;

    @Column(name = "CNTC_TYPE_NUMBER")
    public String cntc_type_number;

    @Column(name = "CNTC_WEIGHT_TYPE_ID")
    public Long cntc_weight_type_id;
}
