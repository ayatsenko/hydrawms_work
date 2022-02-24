package com.idltd.hydramob.entity.tms_brockers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTMSBrockersClaimsTimeLogs {
    @Id
    @Column(name = "CLM_STATUS_ID", nullable = false)
    public Long clm_status_id;

    @Column(name = "CLM_STATUS_NAME")
    public String clm_status_name;

    @Column(name = "CLMTL_DATE")
    public String clmtl_date;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "ROLE_NAME")
    public String role_name;

    @Column(name = "CLM_STATUS_COLOUR")
    public String clm_status_colour;
}
