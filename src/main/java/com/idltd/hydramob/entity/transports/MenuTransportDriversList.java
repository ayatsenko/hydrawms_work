package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTransportDriversList {
    @Id
    @Column(name = "CNTD_ID", nullable = false)
    public Long cntd_id;

    @Column(name = "CNTD_NAME")
    public String cntd_name;
}
