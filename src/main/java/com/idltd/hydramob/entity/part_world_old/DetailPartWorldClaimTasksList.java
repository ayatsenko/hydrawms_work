package com.idltd.hydramob.entity.part_world_old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailPartWorldClaimTasksList {
    @Id
    @Column(name = "CLMTL_ID", nullable = false)
    public Long clmtl_id;

    @Column(name = "CLM_ID")
    public Long clm_id;

    @Column(name = "CLM_TASK_ID")
    public Long clm_task_id;

    @Column(name = "START_USER_ID")
    public Long start_user_id;

    @Column(name = "START_DATE")
    public String start_date;

    @Column(name = "END_USER_ID")
    public Long end_user_id;

    @Column(name = "END_DATE")
    public String end_date;

    @Column(name = "CLMTL_NOTE")
    public String clmtl_note;

    @Column(name = "DEADLINE_DATE")
    public String deadline_date;
}
