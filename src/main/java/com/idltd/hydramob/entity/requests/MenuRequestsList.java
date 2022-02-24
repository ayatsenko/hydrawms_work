package com.idltd.hydramob.entity.requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuRequestsList {
    @Id
    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "REQ_NAME")
    public String req_name;

    @Column(name = "REQ_USER_ID")
    public Long req_user_id;

    @Column(name = "REQ_USER_NAME")
    public String req_user_name;

    @Column(name = "REQ_DATE")
    public String req_date;

    @Column(name = "REQ_OPS_NAME")
    public String req_ops_name;

    @Column(name = "REQ_STATUS_ID")
    public Long req_status_id;

    @Column(name = "REQ_STATUS_SNAME")
    public String req_status_sname;

    @Column(name = "REQ_STATUS_COLOUR")
    public String req_status_colour;

    @Column(name = "REQ_RESULT_STATUS_SNAME")
    public String req_result_status_sname;

    @Column(name = "REQ_SUB_CHECK")
    public String req_sub_check;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;
}
