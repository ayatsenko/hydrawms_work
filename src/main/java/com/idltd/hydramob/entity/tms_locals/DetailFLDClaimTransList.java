package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailFLDClaimTransList {
    @Id
    @Column(name = "CLMTRL_ID", nullable = false)
    public Long clmtrl_id;

    @Column(name = "CLM_ID")
    public Long clm_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNTC_TYPE_ID")
    public Long cntc_type_id;

    @Column(name = "CNTC_ID")
    public Long cntc_id;

    @Column(name = "CNTT_TYPE_ID")
    public Long cntt_type_id;

    @Column(name = "CNTT_ID")
    public Long cntt_id;

    @Column(name = "CNTD_ID")
    public Long cntd_id;
}
