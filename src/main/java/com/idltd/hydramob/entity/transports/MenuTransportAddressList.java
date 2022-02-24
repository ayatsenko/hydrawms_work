package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTransportAddressList {
    @Id
    @Column(name = "CNT_ADD_ID", nullable = false)
    public Long cnt_add_id;

    @Column(name = "ADD_TYPE_SNAME")
    public String add_type_sname;

    @Column(name = "CNT_ADD_VALUE")
    public String cnt_add_value;
}
