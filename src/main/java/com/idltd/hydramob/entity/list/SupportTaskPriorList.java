package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPORT_TASK_PRIOR")
public class SupportTaskPriorList {
    @Id
    @Column(name = "SPTP_ID", nullable = false)
    public Long sptp_id;

    @Column(name = "SPTP_NAME", nullable = false)
    public String sptp_name;

    @Column(name = "SPTP_SNAME", nullable = false)
    public String sptp_sname;

    @Column(name = "SPTP_DESCRIPTION", nullable = false)
    public String sptp_description;

    @Column(name = "SPTP_COLOR", nullable = false)
    public String sptp_color;
}
