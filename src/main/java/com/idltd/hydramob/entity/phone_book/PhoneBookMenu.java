package com.idltd.hydramob.entity.phone_book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneBookMenu {
    @Id
    @Column(name = "RN_ID")
    public Long rn_id;

    @Column(name = "PB_TYPE_ID")
    public Long pb_type_id;

    @Column(name = "CC_ID")
    public Long cc_id;    
    
    @Column(name = "PB_NAME")
    public String pb_name;

    @Column(name = "PB_COMPANY")
    public String pb_company;

    @Column(name = "PB_PHONE_LINK")
    public String pb_phone_link;

    @Column(name = "PB_PHONE")
    public String pb_phone;

    @Column(name = "PB_EMAIL_LINK")
    public String pb_email_link;

    @Column(name = "PB_EMAIL")
    public String pb_email;
}
