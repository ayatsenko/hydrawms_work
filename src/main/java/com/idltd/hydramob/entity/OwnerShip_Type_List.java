package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OwnerShip_Type_List {
    @Id
    public Long owt_id;

    @Column(name = "OWT_NAME", nullable = false)
    public String owt_name;
}