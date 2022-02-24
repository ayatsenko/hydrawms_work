package com.idltd.hydramob.entity.client_tenders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientTendersList {
    @Id
    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "REQ_NAME")
    public String req_name;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "REQ_DATE")
    public String req_date;

    @Column(name = "REQ_ANSWER_TIME")
    public String req_answer_time;

    @Column(name = "REQ_TEND_DATE")
    public String req_tend_date;

    @Column(name = "REQ_TEND_END_DATE")
    public String req_tend_end_date;

    @Column(name = "REQ_OPS_USERNAME")
    public String req_ops_username;

    @Column(name = "REQ_OPS_ANSWER")
    public String req_ops_answer;

    @Column(name = "TEND_STATUS_ID")
    public Long tend_status_id;

    @Column(name = "TEND_STATUS_SNAME")
    public String tend_status_sname;

    @Column(name = "TEND_STATUS_COLOUR")
    public String tend_status_colour;

    @Column(name = "REQ_TEND_STATUS_SNAME")
    public String req_tend_status_sname;

    @Column(name = "REQ_ACTION")
    public Long req_action;

    @Column(name = "REQ_SHOW")
    public Long req_show;
}
