package com.idltd.hydramob.entity.full_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimReportsList {
    @Id
    @Column(name = "REPORT_ID", nullable = false)
    public Long report_id;

    @Column(name = "REPORT_NAME")
    public String report_name;
}
