package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name = "LOCATIONS")
public class Location {
    @Id
    @SequenceGenerator( name = "LOCATIONS_SEQ", sequenceName = "LOCATIONS_SEQ", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "LOCATIONS_SEQ")
    public Long loc_id;

    @Column(name = "LOC_LOCATION", nullable = false)
    public String loc_location;

    @Column(name = "LOC_PARTNERSHIP", nullable = false)
    public String loc_partnership;

    @Column(name = "LOC_ADDRESS", nullable = false)
    public String loc_address;

    @Column(name = "LOC_CITY", nullable = false)
    public String loc_city;

    @Column(name = "LOC_ZIPCODE", nullable = false)
    public String loc_zipcode;

    @Column(name = "LOC_COUNTRY_ISO2", nullable = false)
    public String loc_country_iso2;

    @Column(name = "LOC_PHONE", nullable = false)
    public String loc_phone;
}
