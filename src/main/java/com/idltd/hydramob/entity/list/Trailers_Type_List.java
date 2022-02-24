package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAGENT_TRAILER_TYPE")
public class Trailers_Type_List {
    @Id
    public Long cntt_type_id;

    @Column(name = "CNTT_TYPE_SNAME", nullable = false)
    public String cntt_type_sname;
}