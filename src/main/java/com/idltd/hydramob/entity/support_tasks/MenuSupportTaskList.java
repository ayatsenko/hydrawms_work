package com.idltd.hydramob.entity.support_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSupportTaskList {
    @Id
    @Column(name = "SPT_ID", nullable = false)
    public Long spt_id;

    @Column(name = "SPT_NAME", nullable = false)
    public String spt_name;

    @Column(name = "SPTP_ID", nullable = false)
    public Long sptp_id;

    @Column(name = "SPTP_SNAME", nullable = false)
    public String sptp_sname;

    @Column(name = "SPTP_COLOR", nullable = false)
    public String sptp_color;

    @Column(name = "SPTT_SNAME", nullable = false)
    public String sptt_sname;

    @Column(name = "SPTT_FACT_TIME", nullable = false)
    public Long sptt_fact_time;

    @Column(name = "SPTT_COST_TIME", nullable = false)
    public Long sptt_cost_time;

    @Column(name = "SPTT_COST_ALL_TIME", nullable = false)
    public Long sptt_cost_all_time;

    @Column(name = "SPTS_ID", nullable = false)
    public Long spts_id;

    @Column(name = "SPTS_SNAME", nullable = false)
    public String spts_sname;

    @Column(name = "SPTS_COLOR", nullable = false)
    public String spts_color;

    @Column(name = "SPT_AUTHOR_NAME", nullable = false)
    public String spt_author_name;

    @Column(name = "SPT_WORK_NAME", nullable = false)
    public String spt_work_name;

    @Column(name = "SPT_CREATE_DATE", nullable = false)
    public String spt_create_date;

    @Column(name = "SPT_PLAN_DATE", nullable = false)
    public String spt_plan_date;

    @Column(name = "SPT_TEST_DATE", nullable = false)
    public String spt_test_date;

    @Column(name = "SPT_END_DATE", nullable = false)
    public String spt_end_date;
}
