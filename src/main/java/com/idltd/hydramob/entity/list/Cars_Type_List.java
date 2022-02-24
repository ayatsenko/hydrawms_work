package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAGENT_CAR_TYPE")
public class Cars_Type_List {
    @Id
    public Long cntc_type_id;

    @Column(name = "CNTC_TYPE_SNAME", nullable = false)
    public String cntc_type_sname;
}