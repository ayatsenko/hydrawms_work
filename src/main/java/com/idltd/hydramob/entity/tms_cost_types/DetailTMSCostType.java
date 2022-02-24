package com.idltd.hydramob.entity.tms_cost_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTMSCostType {
    @Id
    @Column(name = "CLMC_TYPE_ID", nullable = false)
    public Long clmc_type_id;

    @Column(name = "CLMC_TYPE_SNAME", nullable = false)
    public String clmc_type_sname;

    @Column(name = "CLMC_TYPE_NAME")
    public String clmc_type_name;

    @Column(name = "CLMC_TYPE_DESCRIPTION")
    public String clmc_type_description;

    @Column(name = "CLMC_TYPE_COLOUR")
    public String clmc_type_colour;

    @Column(name = "CLMC_SOURCE_ID")
    public Long clmc_source_id;
}
