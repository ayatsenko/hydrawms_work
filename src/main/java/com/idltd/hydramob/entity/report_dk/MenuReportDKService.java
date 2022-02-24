package com.idltd.hydramob.entity.report_dk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportDKService {

    @Column(name = "DK_PAID_TYPE_ID")
    public Long dk_paid_type_id;

    @Id
    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "SER_NAME")
    public String ser_name;

    @Column(name = "PREPAYING_SUM")
    public String prepaying_sum;

    @Column(name = "PREPAYING_PERSENT")
    public String prepaying_persent;

    @Column(name = "EXPIRED_SUM")
    public String expired_sum;

    @Column(name = "EXPIRED_PERSENT")
    public String expired_persent;

    @Column(name = "TROUBL_SUM")
    public String troubl_sum;

    @Column(name = "TROUBL_PERSENT")
    public String troubl_persent;

    @Column(name = "ALL_SUM")
    public String all_sum;

    @Column(name = "ALL_PERSENT")
    public String all_persent;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;

    @Column(name = "TOTAL_PERSENT")
    public String total_persent;
}
