package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuProjectsPaymentsList {
    @Id
    @Column(name = "PR_PAY_ID", nullable = false)
    public Long pr_pay_id;

    @Column(name = "PR_PAY_TYPE_ID", nullable = false)
    public Long pr_pay_type_id;

    @Column(name = "PR_PAY_TYPE_SNAME")
    public String pr_pay_type_sname;

    @Column(name = "PR_PAY_NAME")
    public String pr_pay_name;

    @Column(name = "CURRENCY_ID")
    public Long currency_id;

    @Column(name = "CURRENCY_NAME")
    public String currency_name;

    @Column(name = "PR_PAY_SUM")
    public String pr_pay_sum;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "PR_PAY_PLAN_START_DATE")
    public String pr_pay_plan_start_date;

    @Column(name = "PR_PAY_PLAN_END_DATE")
    public String pr_pay_plan_end_date;

    @Column(name = "PR_PAY_APPOVE_CHECK")
    public Long pr_pay_appove_check;
}
