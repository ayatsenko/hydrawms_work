package com.idltd.hydramob.entity.phone_book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneBookMenuDetail {
    @Id
    public Long cc_id;

    @Column(name = "CC_PERSON")
    public String cc_person;

    @Column(name = "CNT_ID")
    public Long cnt_id;
    
    @Column(name = "CC_TYPE_ID")
    public Long cc_type_id;

    @Column(name = "CC_NOTE")
    public String cc_note;

    @Column(name = "CC_POSITION")
    public String cc_position;

    @Column(name = "CC_MAIN_PHONE")
    public String cc_main_phone;

    @Column(name = "CC_SECOND_PHONE")
    public String cc_second_phone;

    @Column(name = "CC_EMAIL")
    public String cc_email;

    @Column(name = "CC_SKYPE")
    public String cc_skype;

    @Column(name = "CC_MAIN_OFFICE_PHONE")
    public String cc_main_office_phone;

    @Column(name = "CC_SECOND_OFFICE_PHONE")
    public String cc_second_office_phone;
}
