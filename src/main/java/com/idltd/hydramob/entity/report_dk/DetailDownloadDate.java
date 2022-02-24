package com.idltd.hydramob.entity.report_dk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailDownloadDate {
    @Id
    @Column(name = "ADD_DATE", nullable = false)
    public String add_date;
}
