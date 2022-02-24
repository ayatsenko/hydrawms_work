package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_TYPE")
public class Request_Type_List {
    @Id
    public Long req_type_id;

    @Column(name = "REQ_TYPE_SNAME", nullable = false)
    public String req_type_sname;
}