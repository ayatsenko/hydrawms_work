package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contragent_Status_List {
    @Id
    public Long cs_id;

    @Column(name = "CS_NAME", nullable = false)
    public String cs_name;
}