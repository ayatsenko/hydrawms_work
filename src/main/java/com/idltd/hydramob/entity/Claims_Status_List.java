package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_STATUS")
public class Claims_Status_List {
    @Id
    @Column(name = "CLM_STATUS_ID", nullable = false)
    public Long clm_status_id;

    @Column(name = "CLM_STATUS_NAME", nullable = false)
    public String clm_status_name;

    @Column(name = "CLM_STATUS_SNAME", nullable = false)
    public String clm_status_sname;

    @Column(name = "CLM_STATUS_DESCRIPTION", nullable = false)
    public String clm_status_description;

    @Column(name = "CLM_STATUS_COLOUR", nullable = false)
    public String clm_status_colour;
}