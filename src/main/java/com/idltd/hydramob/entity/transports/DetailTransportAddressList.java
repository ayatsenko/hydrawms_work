package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTransportAddressList {
    @Id
    @Column(name = "CNT_ADD_ID", nullable = false)
    public Long cnt_add_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "ADD_TYPE_ID")
    public Long add_type_id;

    @Column(name = "CNT_ADD_VALUE")
    public String cnt_add_value;

    @Column(name = "CNT_ADD_NOTE")
    public String cnt_add_note;
}
