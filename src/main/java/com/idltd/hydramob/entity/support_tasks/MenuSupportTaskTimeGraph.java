package com.idltd.hydramob.entity.support_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSupportTaskTimeGraph {
    @Id
    @Column(name = "SPTT_ID", nullable = false)
    public Long sptt_id;

    @Column(name = "SPTT_COUNT", nullable = false)
    public Long sptt_count;

    @Column(name = "SPTT_NAME", nullable = false)
    public String sptt_name;

    @Column(name = "SPTT_COLOR", nullable = false)
    public String sptt_color;
}
