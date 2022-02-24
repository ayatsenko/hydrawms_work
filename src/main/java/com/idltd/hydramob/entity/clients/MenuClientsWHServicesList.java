package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientsWHServicesList {
    @Id
    @Column(name = "EXCH_SER_LINK_ID", nullable = false)
    public Long exch_ser_link_id;

    @Column(name = "EXCH_SER_TYPE_ID")
    public Long exch_ser_type_id;

    @Column(name = "EXCH_SER_TYPE_NAME")
    public String exch_ser_type_name;

    @Column(name = "EXCH_SER_WEB_TYPE_ID")
    public Long exch_ser_web_type_id;
}
