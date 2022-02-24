package com.idltd.hydramob.entity.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuCountryMain {
    @Id
    @Column(name = "ID", nullable = false)
    public Long id;

    @Column(name = "COUNTRY_NAMEUA")
    public String country_nameua;

    @Column(name = "VT_ID")
    public Long vt_id;

    @Column(name = "CODE")
    public String code;

    @Column(name = "COUNTRY_ISO2")
    public String country_iso2;

    @Column(name = "COUNTRY_NAMEEN")
    public String country_nameen;

    @Column(name = "COUNTRY_NAMERUS")
    public String country_namerus;
}
