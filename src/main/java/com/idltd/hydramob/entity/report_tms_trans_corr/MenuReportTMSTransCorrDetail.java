package com.idltd.hydramob.entity.report_tms_trans_corr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportTMSTransCorrDetail {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "DOC_COUNT", nullable = false)
    public Long doc_count;

    @Column(name = "DOC_SUM", nullable = false)
    public String doc_sum;

    @Column(name = "TMS_COUNT", nullable = false)
    public Long tms_count;

    @Column(name = "TMS_SUM")
    public String tms_sum;

    @Column(name = "ALL_COUNT")
    public Long all_count;

    @Column(name = "ALL_SUM")
    public String all_sum;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
