package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country_List {
    @Id
    public Long id;

    @Column(name = "COUNTRY_NAMEUA", nullable = false)
    public String country_nameua;

    @Column(name = "COUNTRY_NAMEEN", nullable = false)
    public String country_nameen;
}