package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_RESULT_STATUS")
public class Request_Result_Status_List {
    @Id
    public Long req_result_status_id;

    @Column(name = "REQ_RESULT_STATUS_SNAME", nullable = false)
    public String req_result_status_sname;

    @Column(name = "REQ_RESULT_STATUS_COLOUR", nullable = false)
    public String req_result_status_colour;
}