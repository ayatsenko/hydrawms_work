package com.idltd.hydramob.entity.report_dk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportDKMain {
    @Column(name = "SFA_ID", nullable = false)
    public Long sfa_id;

    @Column(name = "DK_PAID_TYPE_ID")
    public Long dk_paid_type_id;

    @Column(name = "DK_PAID_TYPE_NAME")
    public String dk_paid_type_name;

    @Id
    @Column(name = "DKM_ID")
    public Long dkm_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CURRENCY_ID")
    public Long currency_id;

    @Column(name = "CURRENCY_NAME")
    public String currency_name;

    @Column(name = "PREPAYING_CURRENCY_SUM")
    public String prepaying_currency_sum;

    @Column(name = "PREPAYING_SUM")
    public String prepaying_sum;

    @Column(name = "EXPIRED_CURRENCY_SUM")
    public String expired_currency_sum;

    @Column(name = "EXPIRED_SUM")
    public String expired_sum;

    @Column(name = "TROUBLE_CURRENCY_SUM")
    public String trouble_currency_sum;

    @Column(name = "TROUBL_SUM")
    public String troubl_sum;

    @Column(name = "ALL_CURRENCY_SUM")
    public String all_currency_sum;

    @Column(name = "ALL_SUM")
    public String all_sum;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;

    @Column(name = "APPROVED_ALL_DEBT_SUM")
    public String approved_all_debt_sum;

    @Column(name = "COMMENT_ID")
    public Long comment_id;
}
