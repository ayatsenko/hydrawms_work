package com.idltd.hydramob.entity.part_world_old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimTransLinkList {
    @Id
    @Column(name = "CLMTRL_ID", nullable = false)
    public Long clmtrl_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CNTC_TYPE_SNAME")
    public String cntc_type_sname;

    @Column(name = "CNTC_TYPE_BRAND")
    public String cntc_type_brand;

    @Column(name = "CNTC_TYPE_NUMBER")
    public String cntc_type_number;
}
