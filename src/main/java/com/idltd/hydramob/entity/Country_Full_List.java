package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country_Full_List {
    @Id
    @Column(name = "COUNTRY_ID", nullable = false)
    public Long country_id;

    @Column(name = "COUNTRY_NAMEUA", nullable = false)
    public String country_nameua;

    @Column(name = "COUNTRY_CODE", nullable = false)
    public String country_code;

    @Column(name = "COUNTRY_ISO2", nullable = false)
    public String country_iso2;

    @Column(name = "COUNTRY_NAMEEN", nullable = false)
    public String country_nameen;
}