package com.idltd.hydramob.entity.ops_request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuOpsRequest {
    @Id
    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "REQ_NAME", nullable = false)
    public String req_name;

    @Column(name = "REQ_TYPE_ID", nullable = false)
    public Long req_type_id;

    @Column(name = "REQ_TYPE_SNAME", nullable = false)
    public String req_type_sname;

    @Column(name = "REQ_DATE", nullable = false)
    public String req_date;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "REQ_STATUS", nullable = false)
    public String req_status;

    @Column(name = "REQ_COLOR", nullable = false)
    public String req_color;
}
