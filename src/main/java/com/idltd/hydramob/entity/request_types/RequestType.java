package com.idltd.hydramob.entity.request_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RequestType {
    @Id
    @Column(name = "REQ_TYPE_ID", nullable = false)
    public Long req_type_id;

    @Column(name = "REQ_TYPE_SNAME", nullable = false)
    public String req_type_sname;

    @Column(name = "REQ_TYPE_NAME")
    public String req_type_name;

    @Column(name = "REQ_TYPE_DESCRIPTION")
    public String req_type_description;

    @Column(name = "REQ_TYPE_COLOUR")
    public String req_type_colour;
}
