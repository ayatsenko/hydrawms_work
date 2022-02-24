package com.idltd.hydramob.entity.part_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLGClaimWHList {
    @Id
    @Column(name = "CLMWHL_ID", nullable = false)
    public Long clmwhl_id;

    @Column(name = "CLMLL_ID", nullable = false)
    public Long clmll_id;

    @Column(name = "CLM_LOAD_NAME", nullable = false)
    public String clm_load_name;

    @Column(name = "CLMWHL_START_DATE")
    public String clmwhl_start_date;

    @Column(name = "CLMWHL_END_DATE")
    public String clmwhl_end_date;
}
