package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuProjectsSchemeList {
    @Id
    @Column(name = "PR_PAY_SCHEME_ID", nullable = false)
    public Long pr_pay_scheme_id;

    @Column(name = "CURRENCY_ID", nullable = false)
    public Long currency_id;

    @Column(name = "CURRENCY_SNAME")
    public String currency_sname;

    @Column(name = "PR_PAY_SCHEME_PLAN_SUM")
    public String pr_pay_scheme_plan_sum;

    @Column(name = "PR_PAY_SCHEME_PLAN_DATE")
    public String pr_pay_scheme_plan_date;

    @Column(name = "PR_PAY_SCHEME_FACT_SUM")
    public String pr_pay_scheme_fact_sum;

    @Column(name = "PR_PAY_SCHEME_FACT_DATE")
    public String pr_pay_scheme_fact_date;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
