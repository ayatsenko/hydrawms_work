package com.idltd.hydramob.entity.requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailRequestModalList {
    @Id
    public Long req_id;

    @Column(name = "REQ_TYPE_ID", nullable = false)
    public Long req_type_id;

    @Column(name = "REQ_NAME", nullable = false)
    public String req_name;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "REQ_DATE", nullable = false)
    public String req_date;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "REQ_END_DATE", nullable = false)
    public String req_end_date;

    @Column(name = "REQ_LOAD_PLACE", nullable = false)
    public String req_load_place;

    @Column(name = "REQ_TAX_PLACE", nullable = false)
    public String req_tax_place;

    @Column(name = "REQ_CLEARANCE_PLACE", nullable = false)
    public String req_clearance_place;

    @Column(name = "REQ_UNLOAD_PLACE", nullable = false)
    public String req_unload_place;

    @Column(name = "REQ_CARGO_NOTE", nullable = false)
    public String req_cargo_note;

    @Column(name = "LOAD_TYPE_ID", nullable = false)
    public Long load_type_id;

    @Column(name = "REQ_WEIGHT", nullable = false)
    public String req_weight;

    @Column(name = "REQ_VOLUME", nullable = false)
    public String req_volume;

    @Column(name = "REQ_DIMS", nullable = false)
    public String req_dims;

    @Column(name = "REQ_ADR", nullable = false)
    public String req_adr;

    @Column(name = "REQ_SPECIAL", nullable = false)
    public String req_special;

    @Column(name = "REQ_TIR_TYPE", nullable = false)
    public String req_tir_type;

    @Column(name = "REQ_PROFILES", nullable = false)
    public String req_profiles;

    @Column(name = "REQ_PLAN_PRICE", nullable = false)
    public String req_plan_price;

    @Column(name = "REQ_NOTE", nullable = false)
    public String req_note;

    @Column(name = "REQ_RESULT_TEXT", nullable = false)
    public String req_result_text;

    @Column(name = "REQ_RESULT_STATUS_ID", nullable = false)
    public Long req_result_status_id;

    @Column(name = "REQ_STATUS_ID", nullable = false)
    public Long req_status_id;

    @Column(name = "REQ_OPS_ID", nullable = false)
    public String req_ops_id;

    @Column(name = "REQ_OPS_USER_ID", nullable = false)
    public Long req_ops_user_id;

    @Column(name = "REQ_OPS_DATE", nullable = false)
    public String req_ops_date;

    @Column(name = "REQ_OPS_ANSWER", nullable = false)
    public String req_ops_answer;

    @Column(name = "REQ_OPS_NOTE", nullable = false)
    public String req_ops_note;

    @Column(name = "REQ_PROFIT_PREDICT", nullable = false)
    public String req_profit_predict;

    @Column(name = "REQ_RESULT_NOTE", nullable = false)
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

    @Column(name = "REQ_LOAD_DEEP")
    public String req_load_deep;

    @Column(name = "REQ_LOAD_HIGH")
    public String req_load_high;

    @Column(name = "REQ_LOAD_WIDTH")
    public String req_load_width;

    @Column(name = "REQ_PLAN_CURRENCY_ID")
    public Long req_plan_currency_id;

    @Column(name = "REQ_RESULT_CURRENCY_ID")
    public Long req_result_currency_id;

    @Column(name = "REQ_RESULT_PRICE")
    public String req_result_price;

    @Column(name = "REQ_OPS_CURRENCY_ID")
    public Long req_ops_currency_id;

    @Column(name = "REQ_OPS_PRICE")
    public String req_ops_price;

    @Column(name = "REQ_FIA_STATUS_ID")
    public Long req_fia_status_id;

    @Column(name = "EDIT_TYPE_ID")
    public Long edit_type_id;
}