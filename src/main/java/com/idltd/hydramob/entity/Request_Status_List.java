package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_STATUS")
public class Request_Status_List {
    @Id
    public Long req_status_id;

    @Column(name = "REQ_STATUS_SNAME", nullable = false)
    public String req_status_sname;
}