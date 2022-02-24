package com.idltd.hydramob.entity.tender_statuses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TenderStatus {
    @Id
    @Column(name = "REQ_TEND_STATUS_ID", nullable = false)
    public Long req_tend_status_id;

    @Column(name = "REQ_TEND_STATUS_SNAME", nullable = false)
    public String req_tend_status_sname;

    @Column(name = "REQ_TEND_STATUS_NAME")
    public String req_tend_status_name;

    @Column(name = "REQ_TEND_STATUS_DESCRIPTION")
    public String req_tend_status_description;

    @Column(name = "REQ_TEND_STATUS_COLOUR")
    public String req_tend_status_colour;
}
