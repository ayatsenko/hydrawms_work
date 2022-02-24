package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLDClaimLoadsList {
    @Id
    @Column(name = "CLMLL_ID", nullable = false)
    public Long clmll_id;

    @Column(name = "CLM_LOAD_NAME")
    public String clm_load_name;

    @Column(name = "CLM_LOAD_WEIGHT")
    public String clm_load_weight;

    @Column(name = "CLM_LOAD_DIMS")
    public String clm_load_dims;

    @Column(name = "CLM_LOAD_NOTES")
    public String clm_load_notes;

    @Column(name = "CLMLL_SPACE_COUNT")
    public Long clmll_space_count;

    @Column(name = "CLMLL_LOAD_METERS")
    public String clmll_load_meters;

    @Column(name = "CLMLL_CLIENT_ORDER_NUM")
    public String clmll_client_order_num;
}
