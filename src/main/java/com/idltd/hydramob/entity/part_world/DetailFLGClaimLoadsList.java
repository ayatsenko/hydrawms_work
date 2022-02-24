package com.idltd.hydramob.entity.part_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailFLGClaimLoadsList {
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

    @Column(name = "WEIGHT_TYPE_ID")
    public Long weight_type_id;

    @Column(name = "CLM_LENGHT")
    public Long clm_lenght;

    @Column(name = "CLM_WIDTH")
    public Long clm_width;

    @Column(name = "CLM_HEIGHT")
    public Long clm_height;
}
