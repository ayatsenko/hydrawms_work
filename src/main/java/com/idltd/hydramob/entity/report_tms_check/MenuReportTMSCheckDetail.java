package com.idltd.hydramob.entity.report_tms_check;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportTMSCheckDetail {
    @Id
    @Column(name = "DOC_ID", nullable = false)
    public Long doc_id;

    @Column(name = "ALL_DOC_CHECK", nullable = false)
    public Long all_doc_check;

    @Column(name = "CRM_DOC_CHECK", nullable = false)
    public Long crm_doc_check;

    @Column(name = "SYSTEM_DOC_CHECK", nullable = false)
    public Long system_doc_check;

    @Column(name = "DOC_DATE")
    public String doc_date;

    @Column(name = "DOC_NUM")
    public String doc_num;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "SER_SNAME")
    public String ser_sname;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "OPS_USER_ID")
    public Long ops_user_id;

    @Column(name = "OPS_USER_NAME")
    public String ops_user_name;

    @Column(name = "CRM_PROFIT")
    public String crm_profit;

    @Column(name = "SYSTEM_PROFIT")
    public String system_profit;

    @Column(name = "ALL_RESULT")
    public String all_result;

    @Column(name = "ALL_RESULT_COLOR")
    public String all_result_color;
}
