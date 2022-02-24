package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTransportContactsList {
    @Id
    @Column(name = "CC_ID", nullable = false)
    public Long cc_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CC_PERSON")
    public String cc_person;

    @Column(name = "CC_POSITION")
    public String cc_position;

    @Column(name = "CC_MAIN_PHONE")
    public String cc_main_phone;

    @Column(name = "CC_SECOND_PHONE")
    public String cc_second_phone;

    @Column(name = "CC_MAIN_OFFICE_PHONE")
    public String cc_main_office_phone;

    @Column(name = "CC_SECOND_OFFICE_PHONE")
    public String cc_second_office_phone;

    @Column(name = "CC_EMAIL")
    public String cc_email;

    @Column(name = "CC_SKYPE")
    public String cc_skype;

    @Column(name = "CC_NOTE")
    public String cc_note;
}
