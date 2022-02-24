package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WEIGHT_TYPES")
public class WeightTypeList {
    @Id
    @Column(name = "WEIGHT_TYPE_ID", nullable = false)
    public Long weight_type_id;

    @Column(name = "WEIGHT_TYPE_NAME")
    public String weight_type_name;

    @Column(name = "WEIGHT_TYPE_SNAME")
    public String weight_type_sname;

    @Column(name = "WEIGHT_TYPE_DESCRIPTION")
    public String weight_type_description;

    @Column(name = "WEIGHT_TYPE_COLOUR")
    public String weight_type_colour;

    @Column(name = "VT_ID")
    public Long vt_id;
}
