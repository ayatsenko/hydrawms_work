package com.idltd.hydramob.entity.report_tender_calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportTenderCalendarDetail {
    @Id
    public Long cntserl_id;

    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_SNAME", nullable = false)
    public String ser_sname;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "REQ_TEND_STATUS_COLOUR", nullable = false)
    public String req_tend_status_colour;

    @Column(name = "FIN_1", nullable = false)
    public String fin_1;

    @Column(name = "FIN_1_COLOUR", nullable = false)
    public Integer fin_1_colour;

    @Column(name = "FIN_2", nullable = false)
    public String fin_2;

    @Column(name = "FIN_2_COLOUR", nullable = false)
    public Integer fin_2_colour;

    @Column(name = "FIN_3", nullable = false)
    public String fin_3;

    @Column(name = "FIN_3_COLOUR", nullable = false)
    public Integer fin_3_colour;

    @Column(name = "FIN_4", nullable = false)
    public String fin_4;

    @Column(name = "FIN_4_COLOUR", nullable = false)
    public Integer fin_4_colour;

    @Column(name = "FIN_5", nullable = false)
    public String fin_5;

    @Column(name = "FIN_5_COLOUR", nullable = false)
    public Integer fin_5_colour;

    @Column(name = "FIN_6", nullable = false)
    public String fin_6;

    @Column(name = "FIN_6_COLOUR", nullable = false)
    public Integer fin_6_colour;

    @Column(name = "FIN_7", nullable = false)
    public String fin_7;

    @Column(name = "FIN_7_COLOUR", nullable = false)
    public Integer fin_7_colour;

    @Column(name = "FIN_8", nullable = false)
    public String fin_8;

    @Column(name = "FIN_8_COLOUR", nullable = false)
    public Integer fin_8_colour;

    @Column(name = "FIN_9", nullable = false)
    public String fin_9;

    @Column(name = "FIN_9_COLOUR", nullable = false)
    public Integer fin_9_colour;

    @Column(name = "FIN_10", nullable = false)
    public String fin_10;

    @Column(name = "FIN_10_COLOUR", nullable = false)
    public Integer fin_10_colour;

    @Column(name = "FIN_11", nullable = false)
    public String fin_11;

    @Column(name = "FIN_11_COLOUR", nullable = false)
    public Integer fin_11_colour;

    @Column(name = "FIN_12", nullable = false)
    public String fin_12;

    @Column(name = "FIN_12_COLOUR", nullable = false)
    public Integer fin_12_colour;
}
