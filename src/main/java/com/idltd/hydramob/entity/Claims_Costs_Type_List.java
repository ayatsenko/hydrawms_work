package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_COSTS_TYPE")
public class Claims_Costs_Type_List {
    @Id
    @Column(name = "CLMC_TYPE_ID", nullable = false)
    public Long clmc_type_id;

    @Column(name = "CLMC_TYPE_NAME", nullable = false)
    public String clmc_type_name;

    @Column(name = "CLMC_TYPE_SNAME", nullable = false)
    public String clmc_type_sname;

    @Column(name = "CLMC_TYPE_DESCRIPTION", nullable = false)
    public String clmc_type_description;

    @Column(name = "CLMC_TYPE_COLOUR", nullable = false)
    public String clmc_type_colour;
}