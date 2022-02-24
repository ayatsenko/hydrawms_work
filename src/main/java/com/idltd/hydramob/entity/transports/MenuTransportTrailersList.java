package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTransportTrailersList {
    @Id
    @Column(name = "CNTT_ID", nullable = false)
    public Long cntt_id;

    @Column(name = "CNTT_TYPE_SNAME")
    public String cntt_type_sname;

    @Column(name = "CNTT_FULLNAME")
    public String cntt_fullname;
}
