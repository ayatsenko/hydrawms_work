package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailFLDClaimsMainTrans {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLM_NUM", nullable = false)
    public String clm_num;

    @Column(name = "TRANS_ID")
    public Long trans_id;

    @Column(name = "CNTC_ID")
    public Long cntc_id;

    @Column(name = "CNTT_ID")
    public Long cntt_id;

    @Column(name = "CNTD_ID")
    public Long cntd_id;
}
