package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientServicesList {
    @Id
    @Column(name = "CNTSERL_ID", nullable = false)
    public Long cntserl_id;

    @Column(name = "SER_NAME")
    public String ser_name;

    @Column(name = "CNTSERL_DATE")
    public String cntserl_date;
}
