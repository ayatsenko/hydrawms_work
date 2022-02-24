package com.idltd.hydramob.entity.tms_brockers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTMSBrockersClaimCostsList {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CLMCL_ID", nullable = false)
    public Long clmcl_id;

    @Column(name = "CLMC_TYPE_ID")
    public Long clmc_type_id;

    @Column(name = "CLMC_TYPE_SNAME")
    public String clmc_type_sname;

    @Column(name = "CURRENCY_NAME")
    public String currency_name;

    @Column(name = "CLMCL_SUM")
    public Float clmcl_sum;

    @Column(name = "CURRENCY_DATE")
    public String currency_date;

    @Column(name = "CLMCL_NOTE")
    public String clmcl_note;

    @Column(name = "CLMC_SOURCE_ID")
    public Long clmc_source_id;

    @Column(name = "CLMC_SOURCE_SNAME")
    public String clmc_source_sname;

    @Column(name = "CLMC_SOURCE_COLOR")
    public String clmc_source_color;
}
