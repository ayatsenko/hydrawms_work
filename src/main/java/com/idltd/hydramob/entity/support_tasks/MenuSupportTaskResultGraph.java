package com.idltd.hydramob.entity.support_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSupportTaskResultGraph {
    @Id
    @Column(name = "SPTS_ID", nullable = false)
    public Long spts_id;

    @Column(name = "SPTS_COUNT", nullable = false)
    public Long spts_count;

    @Column(name = "SPTS_NAME", nullable = false)
    public String spts_name;

    @Column(name = "SPTS_COLOR", nullable = false)
    public String spts_color;
}
