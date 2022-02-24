package com.idltd.hydramob.entity.country_airports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuCountryAirportsMain {
    @Id
    @Column(name = "AEROPORT_ID", nullable = false)
    public Long aeroport_id;

    @Column(name = "AEROPORT_NAME")
    public String aeroport_name;

    @Column(name = "AEROPORT_CITY")
    public String aeroport_city;

    @Column(name = "AEROPORT_CODE")
    public String aeroport_code;

    @Column(name = "VT_ID")
    public Long vt_id;

    @Column(name = "COUNTRY_ID")
    public Long country_id;

    @Column(name = "COUNTRY_NAME")
    public String country_name;
}
