package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_TYPE")
public class Address_Type_List {
    @Id
    public Long add_type_id;

    @Column(name = "ADD_TYPE_SNAME", nullable = false)
    public String add_type_sname;
}