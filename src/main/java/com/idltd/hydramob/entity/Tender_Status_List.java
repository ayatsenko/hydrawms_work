package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TENDER_STATUS")
public class Tender_Status_List {
    @Id
    public Long tend_status_id;

    @Column(name = "TEND_STATUS_SNAME", nullable = false)
    public String tend_status_sname;
}