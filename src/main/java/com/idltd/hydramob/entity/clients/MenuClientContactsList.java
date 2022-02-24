package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientContactsList {
    @Id
    @Column(name = "CC_ID", nullable = false)
    public Long cc_id;

    @Column(name = "CC_PERSON")
    public String cc_person;

    @Column(name = "CC_POSITION")
    public String cc_position;

    @Column(name = "CC_PHONE")
    public String cc_phone;

    @Column(name = "CC_EMAIL")
    public String cc_email;
}
