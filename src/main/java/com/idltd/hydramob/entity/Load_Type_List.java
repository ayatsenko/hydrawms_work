package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOAD_TYPE")
public class Load_Type_List {
    @Id
    public Long load_type_id;

    @Column(name = "LOAD_TYPE_SNAME", nullable = false)
    public String load_type_sname;
}