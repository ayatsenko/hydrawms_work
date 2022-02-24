package com.idltd.hydramob.entity.request_statuses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RequestStatus {
    @Id
    @Column(name = "REQ_STATUS_ID", nullable = false)
    public Long req_status_id;

    @Column(name = "REQ_STATUS_SNAME", nullable = false)
    public String req_status_sname;

    @Column(name = "REQ_STATUS_NAME")
    public String req_status_name;

    @Column(name = "REQ_STATUS_DESCRIPTION")
    public String req_status_description;

    @Column(name = "REQ_STATUS_COLOUR")
    public String req_status_colour;

    @Column(name = "REQ_STATUS_ORDER")
    public Long req_status_order;
}
