package com.idltd.hydramob.entity.requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_FIASCO_STATUS")
public class RequestFiascoStatusList {
    @Id
    public Long req_fia_status_id;

    @Column(name = "REQ_FIA_STATUS_NAME")
    public String req_fia_status_name;

    @Column(name = "REQ_FIA_STATUS_SNAME")
    public String req_fia_status_sname;

    @Column(name = "REQ_FIA_STATUS_DESCRIPTION")
    public String req_fia_status_description;

    @Column(name = "REQ_FIA_STATUS_COLOUR")
    public String req_fia_status_colour;
}