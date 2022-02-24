package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_AIM_TYPES")
public class Claims_Aim_Type_List {
    @Id
    @Column(name = "CLM_AIM_TYPE_ID", nullable = false)
    public Long clm_aim_type_id;

    @Column(name = "CLM_AIM_TYPE_NAME", nullable = false)
    public String clm_aim_type_name;

    @Column(name = "CLM_AIM_TYPE_SNAME", nullable = false)
    public String clm_aim_type_sname;

    @Column(name = "CLM_AIM_TYPE_DESCRIPTION", nullable = false)
    public String clm_aim_type_description;

    @Column(name = "CLM_AIM_TYPE_COLOUR", nullable = false)
    public String clm_aim_type_colour;
}