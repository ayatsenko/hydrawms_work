package com.idltd.hydramob.entity.report_tender_calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="TENDER_STATUS")
public class ReportTenderStatusList {
    @Id
    public Long tend_status_id;

    @Column(name = "tend_status_sname", nullable = false)
    public String tend_status_sname;

    @Column(name = "TEND_STATUS_COLOUR", nullable = false)
    public String tend_status_colour;
}
