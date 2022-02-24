package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientStatisticsList {
    @Id
    @Column(name = "CNTS_ID", nullable = false)
    public Long cnts_id;

    @Column(name = "CNTS_NAME")
    public String cnts_name;

    @Column(name = "CNTS_COUNT")
    public String cnts_count;
}
