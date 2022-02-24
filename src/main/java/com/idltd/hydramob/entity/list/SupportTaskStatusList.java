package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPORT_TASK_STATUS")
public class SupportTaskStatusList {
    @Id
    @Column(name = "SPTS_ID", nullable = false)
    public Long spts_id;

    @Column(name = "SPTS_NAME", nullable = false)
    public String spts_name;

    @Column(name = "SPTS_SNAME", nullable = false)
    public String spts_sname;

    @Column(name = "SPTS_DESCRIPTION", nullable = false)
    public String spts_description;

    @Column(name = "SPTS_COLOR", nullable = false)
    public String spts_color;
}
