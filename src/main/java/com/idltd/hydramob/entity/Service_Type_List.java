package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CRM_SERVICE")
public class Service_Type_List {
    @Id
    public Long ser_id;

    @Column(name = "SER_SNAME", nullable = false)
    public String ser_sname;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;
}