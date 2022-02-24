package com.idltd.hydramob.entity.report_phonebook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportPhoneBookDetail {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE", nullable = false)
    public String cnt_identifycode;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "CNT_SOURCE_ID", nullable = false)
    public Long cnt_source_id;

    @Column(name = "CNT_SOURCE_SNAME", nullable = false)
    public String cnt_source_sname;

    @Column(name = "CC_PERSON", nullable = false)
    public String cc_person;

    @Column(name = "CC_POSITION", nullable = false)
    public String cc_position;

    @Column(name = "CC_MAIN_PHONE", nullable = false)
    public String cc_main_phone;

    @Column(name = "CC_SECOND_PHONE", nullable = false)
    public String cc_second_phone;

    @Column(name = "CC_MAIN_OFFICE_PHONE", nullable = false)
    public String cc_main_office_phone;

    @Column(name = "CC_SECOND_OFFICE_PHONE", nullable = false)
    public String cc_second_office_phone;

    @Column(name = "CC_EMAIL", nullable = false)
    public String cc_email;

    @Column(name = "CC_SKYPE", nullable = false)
    public String cc_skype;
}
