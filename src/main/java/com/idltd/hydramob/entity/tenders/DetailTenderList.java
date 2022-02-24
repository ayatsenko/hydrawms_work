package com.idltd.hydramob.entity.tenders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTenderList {
    @Id
    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "REQ_TYPE_ID", nullable = false)
    public Long req_type_id;

    @Column(name = "REQ_NAME", nullable = false)
    public String req_name;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "REQ_DATE")
    public String req_date;

    @Column(name = "TEND_STATUS_ID")
    public Long tend_status_id;

    @Column(name = "REQ_TEND_STATUS_ID")
    public Long req_tend_status_id;

    @Column(name = "REQ_OPS_ID")
    public Long req_ops_id;

    @Column(name = "REQ_OPS_USER_ID")
    public Long req_ops_user_id;

    @Column(name = "REQ_OPS_DATE")
    public String req_ops_date;

    @Column(name = "REQ_OPS_ANSWER")
    public String req_ops_answer;

    @Column(name = "REQ_OPS_NOTE")
    public String req_ops_note;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "REQ_END_DATE")
    public String req_end_date;

    @Column(name = "REQ_NOTE")
    public String req_note;

    @Column(name = "REQ_TEND_DATE")
    public String req_tend_date;

    @Column(name = "REQ_TEND_RUNWAY")
    public String req_tend_runway;

    @Column(name = "REQ_TEND_AUTOTYPE")
    public String req_tend_autotype;

    @Column(name = "REQ_TEND_COUNT")
    public String req_tend_count;

    @Column(name = "REQ_RESULT_TEXT")
    public String req_result_text;

    @Column(name = "REQ_RESULT_STATUS_ID")
    public Long req_result_status_id;

    @Column(name = "REQ_TEND_END_DATE")
    public String req_tend_end_date;

    @Column(name = "REQ_PROFIT_PREDICT")
    public String req_profit_predict;

    @Column(name = "REQ_RESULT_NOTE")
    public String req_result_note;

    @Column(name = "REQ_WORK_NOTE")
    public String req_work_note;

    @Column(name = "REQ_DOC_NUMBER")
    public String req_doc_number;

    @Column(name = "REQ_DOC_START_DATE")
    public String req_doc_start_date;

    @Column(name = "REQ_DOC_END_DATE")
    public String req_doc_end_date;

    @Column(name = "REQ_WORK_START_DATE")
    public String req_work_start_date;

    @Column(name = "EDIT_TYPE_ID")
    public Long edit_type_id;

    @Column(name = "TEND_FIA_STATUS_ID")
    public Long tend_fia_status_id;
}
