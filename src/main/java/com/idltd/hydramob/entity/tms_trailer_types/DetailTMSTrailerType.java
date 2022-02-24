package com.idltd.hydramob.entity.tms_trailer_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTMSTrailerType {
    @Id
    @Column(name = "CNTT_TYPE_ID", nullable = false)
    public Long cntt_type_id;

    @Column(name = "CNTT_TYPE_SNAME", nullable = false)
    public String cntt_type_sname;

    @Column(name = "CNTT_TYPE_NAME")
    public String cntt_type_name;

    @Column(name = "CNTT_TYPE_DESCRIPTION")
    public String cntt_type_description;

    @Column(name = "CNTT_TYPE_COLOUR")
    public String cntt_type_colour;
}
