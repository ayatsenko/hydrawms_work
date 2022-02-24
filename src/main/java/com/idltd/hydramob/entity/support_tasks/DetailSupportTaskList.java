package com.idltd.hydramob.entity.support_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailSupportTaskList {
    @Id
    @Column(name = "SPT_ID", nullable = false)
    public Long spt_id;

    @Column(name = "SPT_NAME", nullable = false)
    public String spt_name;

    @Column(name = "SPT_DESCRIPTION", nullable = false)
    public String spt_description;

    @Column(name = "SPTP_ID", nullable = false)
    public Long sptp_id;

    @Column(name = "SPTT_ID", nullable = false)
    public Long sptt_id;

    @Column(name = "SPTT_NUMBER", nullable = false)
    public String sptt_number;

    @Column(name = "SPTT_PLAN_TIME", nullable = false)
    public Long sptt_plan_time;

    @Column(name = "SPTT_FACT_TIME", nullable = false)
    public Long sptt_fact_time;

    @Column(name = "SPTS_ID", nullable = false)
    public Long spts_id;

    @Column(name = "SPT_AUTHOR_ID", nullable = false)
    public Long spt_author_id;

    @Column(name = "SPT_CONT_ID", nullable = false)
    public Long spt_cont_id;

    @Column(name = "SPT_WORK_ID", nullable = false)
    public Long spt_work_id;

    @Column(name = "SPT_CREATE_DATE", nullable = false)
    public String spt_create_date;

    @Column(name = "SPT_PLAN_DATE", nullable = false)
    public String spt_plan_date;

    @Column(name = "SPT_TEST_DATE", nullable = false)
    public String spt_test_date;

    @Column(name = "SPT_END_DATE", nullable = false)
    public String spt_end_date;
}
