package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TMS_Trailers_List {
    @Id
    @Column(name = "CNTT_ID", nullable = false)
    public Long cntt_id;

    @Column(name = "CNTT_TYPE_ID")
    public Long cntt_type_id;

    @Column(name = "CNTT_TYPE_BRAND")
    public String cntt_type_brand;

    @Column(name = "CNTT_TYPE_NUMBER")
    public String cntt_type_number;

    @Column(name = "CNTT_FULLNAME")
    public String cntt_fullname;
}