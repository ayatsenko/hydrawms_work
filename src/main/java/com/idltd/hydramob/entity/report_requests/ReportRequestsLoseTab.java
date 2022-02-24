package com.idltd.hydramob.entity.report_requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportRequestsLoseTab {
    @Id
    public Long req_id;

    @Column(name = "REQ_TYPE_ID", nullable = false)
    public Long req_type_id;

    @Column(name = "REQ_TYPE_NAME", nullable = false)
    public String req_type_name;

    @Column(name = "REQ_NAME", nullable = false)
    public String req_name;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USERNAME", nullable = false)
    public String username;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "REQ_DATE", nullable = false)
    public String req_date;

    @Column(name = "REQ_OPS_ID", nullable = false)
    public String req_ops_id;

    @Column(name = "REQ_OPS_DATE", nullable = false)
    public String req_ops_date;

    @Column(name = "REQ_OPS_USER_ID", nullable = false)
    public Long req_ops_user_id;

    @Column(name = "REQ_OPS_USERNAME", nullable = false)
    public String req_ops_username;

    @Column(name = "REQ_OPS_ANSWER", nullable = false)
    public String req_ops_answer;

    @Column(name = "REQ_STATUS_ID", nullable = false)
    public Long req_status_id;

    @Column(name = "REQ_STATUS_SNAME", nullable = false)
    public String req_status_sname;

    @Column(name = "REQ_STATUS_COLOUR", nullable = false)
    public String req_status_colour;

    @Column(name = "REQ_TEND_STATUS_ID", nullable = false)
    public Long req_tend_status_id;

    @Column(name = "REQ_TEND_STATUS_SNAME", nullable = false)
    public String req_tend_status_sname;

    @Column(name = "REQ_RESULT_STATUS_ID", nullable = false)
    public Long req_result_status_id;

    @Column(name = "REQ_RESULT_STATUS_SNAME", nullable = false)
    public String req_result_status_sname;

    @Column(name = "REQ_RESULT_TEXT", nullable = false)
    public String req_result_text;

    @Column(name = "REQ_ANSWER_TIME", nullable = false)
    public String req_answer_time;

    @Column(name = "REQ_RESULT_ALL_STATUS", nullable = false)
    public String req_result_all_status;

    @Column(name = "REQ_PROFIT_PREDICT", nullable = false)
    public String req_profit_predict;

    @Column(name = "REQ_FIA_STATUS_ID", nullable = false)
    public Long req_fia_status_id;

    @Column(name = "REQ_FIA_STATUS_SNAME", nullable = false)
    public String req_fia_status_sname;
}
