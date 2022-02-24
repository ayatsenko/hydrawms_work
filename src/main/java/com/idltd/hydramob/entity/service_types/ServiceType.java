package com.idltd.hydramob.entity.service_types;

import javax.persistence.*;

@Entity
public class ServiceType {
    @Id
    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_SNAME", nullable = false)
    public String ser_sname;

    @Column(name = "SER_NAME")
    public String ser_name;

    @Column(name = "SER_DESCRIPTION")
    public String ser_description;

    @Column(name = "SER_COLOR")
    public String ser_color;
}
