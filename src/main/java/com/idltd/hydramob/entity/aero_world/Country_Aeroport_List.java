package com.idltd.hydramob.entity.aero_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY_AEROPORTS")
public class Country_Aeroport_List {
    @Id
    @Column(name = "AEROPORT_ID", nullable = false)
    public Long aeroport_id;

    @Column(name = "AEROPORT_NAME", nullable = false)
    public String aeroport_name;

    @Column(name = "AEROPORT_CITY", nullable = false)
    public String aeroport_city;

    @Column(name = "AEROPORT_CODE", nullable = false)
    public String aeroport_code;

    @Column(name = "VT_ID", nullable = false)
    public Long vt_id;

    @Column(name = "COUNTRY_ID", nullable = false)
    public Long country_id;
}