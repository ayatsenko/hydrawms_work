package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuProjectsDetailList {
    @Id
    @Column(name = "PR_ID", nullable = false)
    public Long pr_id;

    @Column(name = "PR_NUMBER")
    public String pr_number;

    @Column(name = "PR_SNAME")
    public String pr_sname;

    @Column(name = "PR_NAME")
    public String pr_name;

    @Column(name = "PR_CREATE_DATE")
    public String pr_create_date;

    @Column(name = "PR_TYPE_ID")
    public Long pr_type_id;

    @Column(name = "PR_TYPE_NAME")
    public String pr_type_name;

    @Column(name = "PR_STATUS_ID")
    public Long pr_status_id;

    @Column(name = "PR_STATUS_SNAME")
    public String pr_status_sname;

    @Column(name = "PR_END_DATE")
    public String pr_end_date;

    @Column(name = "AUTHOR_ID")
    public Long author_id;

    @Column(name = "AUTHOR_NAME")
    public String author_name;

    @Column(name = "PR_DESCRIPTION")
    public String pr_description;

    @Column(name = "PR_PRIOR_ID")
    public Long pr_prior_id;

    @Column(name = "PR_PRIOR_NAME")
    public String pr_prior_name;

    @Column(name = "PR_PLAN_START_DATE")
    public String pr_plan_start_date;

    @Column(name = "PR_PLAN_END_DATE")
    public String pr_plan_end_date;

    @Column(name = "PR_FACT_START_DATE")
    public String pr_fact_start_date;

    @Column(name = "PR_FACT_END_DATE")
    public String pr_fact_end_date;

    @Column(name = "PR_RESULT")
    public String pr_result;
}
