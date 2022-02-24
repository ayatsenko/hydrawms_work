package com.idltd.hydramob.entity.clients_classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientsClassesStatList {
    @Id
    @Column(name = "CNTS_ID", nullable = false)
    public Long cnts_id;

    @Column(name = "CNTS_NAME")
    public String cnts_name;

    @Column(name = "CNTS_COUNT")
    public String cnts_count;

    @Column(name = "CNTS_ALL_COUNT")
    public String cnts_all_count;
}
