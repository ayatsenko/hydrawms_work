package com.idltd.hydramob.entity.client_docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientBillsList {
    @Id
    @Column(name = "CNT_BILL_ID", nullable = false)
    public Long cnt_bill_id;

    @Column(name = "CNT_BILL_NUM")
    public String cnt_bill_num;

    @Column(name = "CNT_BILL_DATE")
    public String cnt_bill_date;

    @Column(name = "CNT_BILL_ACT_DATE")
    public String cnt_bill_act_date;

    @Column(name = "CNT_BILL_PLAN_PAY_DATE")
    public String cnt_bill_plan_pay_date;

    @Column(name = "CURRENCY_ID")
    public Long currency_id;

    @Column(name = "CURRENCY_NAME")
    public String currency_name;

    @Column(name = "CNT_BILL_CUR_SUM")
    public String cnt_bill_cur_sum;

    @Column(name = "CNT_BILL_SUM")
    public String cnt_bill_sum;

    @Column(name = "CNT_BILL_CLOSE_DATE")
    public String cnt_bill_close_date;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
