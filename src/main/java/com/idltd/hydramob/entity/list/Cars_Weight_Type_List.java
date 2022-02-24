package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAGENT_CAR_WEIGHT_TYPE")
public class Cars_Weight_Type_List {
    @Id
    @Column(name = "CNTC_WEIGHT_TYPE_ID", nullable = false)
    public Long cntc_weight_type_id;

    @Column(name = "CNTC_WEIGHT_TYPE_NAME")
    public String cntc_weight_type_name;

    @Column(name = "CNTC_WEIGHT_TYPE_SNAME")
    public String cntc_weight_type_sname;

    @Column(name = "CNTC_WEIGHT_TYPE_DESCRIPTION")
    public String cntc_weight_type_description;

    @Column(name = "CNTC_WEIGHT_TYPE_COLOUR")
    public String cntc_weight_type_colour;

    @Column(name = "VT_ID")
    public Long vt_id;
}