package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContragentContactList {
    @Id
    public Long cc_id;

    @Column(name = "CC_NAME", nullable = false)
    public String cc_name;
}