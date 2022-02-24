package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimTransPointsTemp {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CLM_CLIENT_NUM")
    public String clm_client_num;

    @Column(name = "LOAD_DATE")
    public String load_date;

    @Column(name = "LOAD_POINT")
    public String load_point;

    @Column(name = "UNLOAD_POINT")
    public String unload_point;
}
