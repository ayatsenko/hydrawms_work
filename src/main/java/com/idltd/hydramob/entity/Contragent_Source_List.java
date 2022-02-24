package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contragent_Source_List {
    @Id
    public Long cnt_source_id;

    @Column(name = "CNT_SOURCE_NAME", nullable = false)
    public String cnt_source_name;

    @Column(name = "CNT_SOURCE_SNAME", nullable = false)
    public String cnt_source_sname;

    @Column(name = "CNT_SOURCE_DESCRIPTION", nullable = false)
    public String cnt_source_description;
}