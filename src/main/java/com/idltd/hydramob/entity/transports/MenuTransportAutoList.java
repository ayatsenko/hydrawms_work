package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTransportAutoList {
    @Id
    @Column(name = "CNTC_ID", nullable = false)
    public Long cntc_id;

    @Column(name = "CNTC_TYPE_SNAME")
    public String cntc_type_sname;

    @Column(name = "CNTC_FULLNAME")
    public String cntc_fullname;

    @Column(name = "CNTC_WEIGHT_TYPE_ID")
    public Long cntc_weight_type_id;

    @Column(name = "CNTC_WEIGHT_TYPE_NAME")
    public String cntc_weight_type_name;
}
