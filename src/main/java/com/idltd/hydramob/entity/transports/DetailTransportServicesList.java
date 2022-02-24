package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTransportServicesList {
    @Id
    @Column(name = "CNTSERL_ID", nullable = false)
    public Long cntserl_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "CNTSERL_DATE")
    public String cntserl_date;
}
