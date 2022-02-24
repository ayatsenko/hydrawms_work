package com.idltd.hydramob.entity.report_tms_trans_profit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportTMSTransProfitDetail {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

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

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
