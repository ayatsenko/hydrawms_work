package com.idltd.hydramob.entity.report_tms_trans_markup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportTMSTransMarkupDetail {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "CLM_COUNT", nullable = false)
    public Long clm_count;

    @Column(name = "CLIENT_SUM")
    public String client_sum;

    @Column(name = "COSTS_SUM")
    public String costs_sum;

    @Column(name = "RESULT")
    public String result;

    @Column(name = "RESULT_COLOR")
    public String result_color;

    @Column(name = "MARKUP_SUM")
    public String markup_sum;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
