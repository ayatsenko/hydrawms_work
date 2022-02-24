package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_COSTS_SOURCE")
public class TMS_Costs_Source_List {
    @Id
    public Long clmc_source_id;

    @Column(name = "CLMC_SOURCE_SNAME", nullable = false)
    public String clmc_source_sname;
}