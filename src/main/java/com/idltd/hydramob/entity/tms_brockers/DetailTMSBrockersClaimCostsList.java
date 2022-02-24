package com.idltd.hydramob.entity.tms_brockers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTMSBrockersClaimCostsList {
    @Id
    @Column(name = "CLMCL_ID", nullable = false)
    public Long clmcl_id;

    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLMC_TYPE_ID", nullable = false)
    public Long clmc_type_id;

    @Column(name = "CURRENCY_ID", nullable = false)
    public Long currency_id;

    @Column(name = "CLMCL_SUM")
    public Float clmcl_sum;

    @Column(name = "CLMCL_NOTE")
    public String clmcl_note;

    @Column(name = "CURRENCY_DATE")
    public String currency_date;
}
