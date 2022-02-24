package com.idltd.hydramob.entity.report_dk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportDKDoc {
    @Id
    @Column(name = "DKMDD_ID", nullable = false)
    public Long dkmdd_id;

    @Column(name = "ORDER_NAME")
    public String order_name;

    @Column(name = "BILL_DATE")
    public String bill_date;

    @Column(name = "PAID_DATE")
    public String paid_date;

    @Column(name = "DAY_COUNT")
    public String day_count;

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

    @Column(name = "COMMENT_ID")
    public Long comment_id;
}
