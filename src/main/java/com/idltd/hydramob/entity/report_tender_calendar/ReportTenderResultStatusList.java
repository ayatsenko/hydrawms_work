package com.idltd.hydramob.entity.report_tender_calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="REQUEST_TEND_STATUS")
public class ReportTenderResultStatusList {
    @Id
    public Long req_tend_status_id;

    @Column(name = "REQ_TEND_STATUS_NAME", nullable = false)
    public String req_tend_status_name;

    @Column(name = "REQ_TEND_STATUS_COLOUR", nullable = false)
    public String req_tend_status_colour;
}
