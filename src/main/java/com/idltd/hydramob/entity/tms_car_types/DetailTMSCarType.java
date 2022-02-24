package com.idltd.hydramob.entity.tms_car_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTMSCarType {
    @Id
    @Column(name = "CNTC_TYPE_ID", nullable = false)
    public Long cntc_type_id;

    @Column(name = "CNTC_TYPE_SNAME", nullable = false)
    public String cntc_type_sname;

    @Column(name = "CNTC_TYPE_NAME")
    public String cntc_type_name;

    @Column(name = "CNTC_TYPE_DESCRIPTION")
    public String cntc_type_description;

    @Column(name = "CNTC_TYPE_COLOUR")
    public String cntc_type_colour;
}
