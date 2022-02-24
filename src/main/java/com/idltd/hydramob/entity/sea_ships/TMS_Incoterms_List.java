package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZCRM.TMS_CLAIMS_INCOTERMS")
public class TMS_Incoterms_List {
    @Id
    @Column(name = "CLM_INCOTERMS_ID", nullable = false)
    public Long clm_incoterms_id;

    @Column(name = "CLM_INCOTERMS_NAME")
    public String clm_incoterms_name;

    @Column(name = "CLM_INCOTERMS_SNAME")
    public String clm_incoterms_sname;

    @Column(name = "CLM_INCOTERMS_DESCRIPTION")
    public String clm_incoterms_description;

    @Column(name = "CLM_INCOTERMS_COLOR")
    public String clm_incoterms_color;
}