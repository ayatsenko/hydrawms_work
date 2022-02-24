package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZCRM.TMS_CLAIMS_BULK_TYPE")
public class TMS_Bulk_Type_List {
    @Id
    @Column(name = "CLM_BULK_TYPE_ID", nullable = false)
    public Long clm_bulk_type_id;

    @Column(name = "CLM_BULK_TYPE_NAME")
    public String clm_bulk_type_name;

    @Column(name = "CLM_BULK_TYPE_SNAME")
    public String clm_bulk_type_sname;

    @Column(name = "CLM_BULK_TYPE_DESCRIPTION")
    public String clm_bulk_type_description;

    @Column(name = "CLM_BULK_TYPE_COLOR")
    public String clm_bulk_type_color;
}