package com.idltd.hydramob.entity.report_tms_trans_cities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportTMSTransCitiesDetail {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "START_DATE")
    public String start_date;

    @Column(name = "START_CITY")
    public String start_city;

    @Column(name = "END_DATE")
    public String end_date;

    @Column(name = "END_CITY")
    public String end_city;

    @Column(name = "START_CURR")
    public String start_curr;

    @Column(name = "START_SUM")
    public String start_sum;

    @Column(name = "END_CURR")
    public String end_curr;

    @Column(name = "END_SUM")
    public String end_sum;

    @Column(name = "PROFIT_SUM")
    public String profit_sum;
}
