package com.idltd.hydramob.entity.client_requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientRequestTimesDetail {
    @Id
    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "FIRST_USER_ID")
    public Long first_user_id;

    @Column(name = "FIRST_USER_NAME")
    public String first_user_name;

    @Column(name = "FIRST_USER_SURNAME")
    public String first_user_surname;

    @Column(name = "FIRST_NOTE")
    public String first_note;

    @Column(name = "FIRST_PLAN_CURRENCY_ID")
    public Long first_plan_currency_id;

    @Column(name = "FIRST_PLAN_CURRENCY_NAME")
    public String first_plan_currency_name;

    @Column(name = "FIRST_PLAN_PRICE")
    public String first_plan_price;


    @Column(name = "RESULT_USER_ID")
    public Long result_user_id;

    @Column(name = "RESULT_USER_NAME")
    public String result_user_name;

    @Column(name = "RESULT_USER_SURNAME")
    public String result_user_surname;

    @Column(name = "RESULT_TEXT")
    public String result_text;

    @Column(name = "RESULT_CURRENCY_ID")
    public Long result_currency_id;

    @Column(name = "RESULT_CURRENCY_NAME")
    public String result_currency_name;

    @Column(name = "RESULT_PRICE")
    public String result_price;


    @Column(name = "REQ_OPS_ID")
    public Long req_ops_id;

    @Column(name = "OPS_USER_ID")
    public Long ops_user_id;

    @Column(name = "OPS_USER_NAME")
    public String ops_user_name;

    @Column(name = "OPS_USER_SURNAME")
    public String ops_user_surname;

    @Column(name = "OPS_NOTE")
    public String ops_note;

    @Column(name = "OPS_CURRENCY_ID")
    public Long ops_currency_id;

    @Column(name = "OPS_CURRENCY_NAME")
    public String ops_currency_name;

    @Column(name = "OPS_PRICE")
    public String ops_price;


    @Column(name = "REQ_DATE")
    public String req_date;

    @Column(name = "REQ_FILL_DATE")
    public String req_fill_date;

    @Column(name = "REQ_FILL_VALUE")
    public String req_fill_value;

    @Column(name = "REQ_FILL_H_VALUE")
    public String req_fill_h_value;


    @Column(name = "REQ_OPS_DATE")
    public String req_ops_date;

    @Column(name = "REQ_OPS_VALUE")
    public String req_ops_value;

    @Column(name = "REQ_OPS_H_VALUE")
    public String req_ops_h_value;


    @Column(name = "REQ_SALE_DATE")
    public String req_sale_date;

    @Column(name = "REQ_SALE_VALUE")
    public String req_sale_value;

    @Column(name = "REQ_SALE_H_VALUE")
    public String req_sale_h_value;


    @Column(name = "REQ_END_DATE")
    public String req_end_date;

    @Column(name = "REQ_END_VALUE")
    public String req_end_value;

    @Column(name = "REQ_END_H_VALUE")
    public String req_end_h_value;


    @Column(name = "REQ_CONTRACT_DATE")
    public String req_contract_date;

    @Column(name = "REQ_CONTRACT_VALUE")
    public String req_contract_value;

    @Column(name = "REQ_CONTRACT_H_VALUE")
    public String req_contract_h_value;


    @Column(name = "REQ_START_DATE")
    public String req_start_date;

    @Column(name = "REQ_START_VALUE")
    public String req_start_value;

    @Column(name = "REQ_START_H_VALUE")
    public String req_start_h_value;


    @Column(name = "REQ_ALL_VALUE")
    public String req_all_value;

    @Column(name = "REQ_ALL_H_VALUE")
    public String req_all_h_value;
}
