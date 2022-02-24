package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPORT_TASK_TYPE")
public class SupportTaskTypeList {
    @Id
    @Column(name = "SPTT_ID", nullable = false)
    public Long sptt_id;

    @Column(name = "SPTT_NAME", nullable = false)
    public String sptt_name;

    @Column(name = "SPTT_SNAME", nullable = false)
    public String sptt_sname;

    @Column(name = "SPTT_DESCRIPTION", nullable = false)
    public String sptt_description;
}
