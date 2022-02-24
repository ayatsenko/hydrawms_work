package com.idltd.hydramob.entity.wms_report_1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSReport1 {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "ADD_DATE")
    public String add_date;

    @Column(name = "ADD_DATE_NAME")
    public String add_date_name;

    @Column(name = "ADD_DATE_CHECK_ID")
    public Long add_date_check_id;

    @Column(name = "CLIENT_ORDERS")
    public String client_orders;

    @Column(name = "CLIENT_DOCS")
    public String client_docs;

    @Column(name = "CLIENT_POST_COUNT")
    public String client_post_count;

    @Column(name = "CLIENT_NOT_SEND_COUNT")
    public String client_not_send_count;

    @Column(name = "DOC_COUNT")
    public String doc_count;

    @Column(name = "DOC_POST_COUNT")
    public String doc_post_count;

    @Column(name = "DOC_NOT_WORK")
    public String doc_not_work;

    @Column(name = "DOC_POST_NOT_WORK")
    public String doc_post_not_work;

    @Column(name = "DOC_POST_DAY_AVG_TIME")
    public String doc_post_day_avg_time;

    @Column(name = "DOC_DAY_AVG_TIME")
    public String doc_day_avg_time;

    @Column(name = "START_TIME")
    public String start_time;

    @Column(name = "END_TIME")
    public String end_time;

    @Column(name = "WORK_TIME")
    public String work_time;

    @Column(name = "PAIR_COUNT")
    public Long pair_count;

    @Column(name = "PAIR_HOUR")
    public String pair_hour;

    @Column(name = "PAIR_POST_COUNT_CHECK")
    public Long pair_post_count_check;
}
