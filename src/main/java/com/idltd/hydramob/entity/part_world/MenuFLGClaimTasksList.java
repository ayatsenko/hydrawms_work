package com.idltd.hydramob.entity.part_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLGClaimTasksList {
    @Id
    @Column(name = "CLMTL_ID", nullable = false)
    public Long clmtl_id;

    @Column(name = "CLM_TASK_SNAME")
    public String clm_task_sname;

    @Column(name = "START_USER_NAME")
    public String start_user_name;

    @Column(name = "START_DATE")
    public String start_date;

    @Column(name = "END_USER_NAME")
    public String end_user_name;

    @Column(name = "END_DATE")
    public String end_date;

    @Column(name = "CLM_TASK_CHECK")
    public Long clm_task_check;

    @Column(name = "CLM_TASK_UNCHECK")
    public Long clm_task_uncheck;

    @Column(name = "DEADLINE_DATE")
    public String deadline_date;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
