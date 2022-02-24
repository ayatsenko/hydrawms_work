package com.idltd.hydramob.entity.report_dk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportDKContract {
    @Id
    @Column(name = "DKMD_ID", nullable = false)
    public Long dkmd_id;

    @Column(name = "CONTRACT")
    public String contract;

    @Column(name = "CURRENCY_ID")
    public Long currency_id;

    @Column(name = "CURRENCY_NAME")
    public String currency_name;

    @Column(name = "PAID_PREMISS")
    public String paid_premiss;

    @Column(name = "APPROVED_DEBT_SUM")
    public String approved_debt_sum;

    @Column(name = "DEBT_MAX_DAY")
    public Long debt_max_day;

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

    @Column(name = "COMMENT_ID")
    public Long comment_id;
}
