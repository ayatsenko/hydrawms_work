package com.idltd.hydramob.entity.part_world_old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuPartWorldClaimWaysList {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CLMWL_ID", nullable = false)
    public Long clmwl_id;

    @Column(name = "CLM_WAY_SNAME")
    public String clm_way_sname;

    @Column(name = "CLM_WAY_DATE")
    public String clm_way_date;

    @Column(name = "CLM_WAY_FACT_DATE")
    public String clm_way_fact_date;

    @Column(name = "CLM_WAY_POINT")
    public String clm_way_point;

    @Column(name = "CLM_WAY_ADDRESS")
    public String clm_way_address;
}
