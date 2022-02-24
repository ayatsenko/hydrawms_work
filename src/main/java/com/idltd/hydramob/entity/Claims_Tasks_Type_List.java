package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_TASKS")
public class Claims_Tasks_Type_List {
    @Id
    @Column(name = "CLM_TASK_ID", nullable = false)
    public Long clm_task_id;

    @Column(name = "CLM_TASK_NAME", nullable = false)
    public String clm_task_name;

    @Column(name = "CLM_TASK_SNAME", nullable = false)
    public String clm_task_sname;

    @Column(name = "CLM_TASK_DESCRIPTION", nullable = false)
    public String clm_task_description;

    @Column(name = "CLM_TASK_COLOUR", nullable = false)
    public String clm_task_colour;
}