package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_TEND_STATUS")
public class Request_Tend_Status_List {
    @Id
    public Long req_tend_status_id;

    @Column(name = "REQ_TEND_STATUS_SNAME", nullable = false)
    public String req_tend_status_sname;

    @Column(name = "REQ_TEND_STATUS_COLOUR", nullable = false)
    public String req_tend_status_colour;
}