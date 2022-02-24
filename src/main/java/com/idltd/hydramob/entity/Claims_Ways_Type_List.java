package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_WAY_TYPE")
public class Claims_Ways_Type_List {
    @Id
    @Column(name = "CLM_WAY_ID", nullable = false)
    public Long clm_way_id;

    @Column(name = "CLM_WAY_NAME", nullable = false)
    public String clm_way_name;

    @Column(name = "CLM_WAY_SNAME", nullable = false)
    public String clm_way_sname;

    @Column(name = "CLM_WAY_DESCRIPTION", nullable = false)
    public String clm_way_description;

    @Column(name = "CLM_WAY_COLOUR", nullable = false)
    public String clm_way_colour;
}