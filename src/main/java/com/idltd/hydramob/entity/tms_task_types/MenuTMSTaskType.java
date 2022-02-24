package com.idltd.hydramob.entity.tms_task_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTMSTaskType {
    @Id
    @Column(name = "CLM_TASK_ID", nullable = false)
    public Long clm_task_id;

    @Column(name = "CLM_TASK_SNAME", nullable = false)
    public String clm_task_sname;

    @Column(name = "CLM_TASK_NAME")
    public String clm_task_name;

    @Column(name = "CLM_TASK_DESCRIPTION")
    public String clm_task_description;

    @Column(name = "CLM_TASK_COLOUR")
    public String clm_task_colour;

    @Column(name = "CLM_TASK_DEFAULT", nullable = false)
    public Long clm_task_default;
}
