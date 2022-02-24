package com.idltd.hydramob.entity.service_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuServiceTypeSystems {
    @Id
    @Column(name = "SERSL_ID", nullable = false)
    public Long sersl_id;

    @Column(name = "SERSL_NAME", nullable = false)
    public String sersl_name;
}
