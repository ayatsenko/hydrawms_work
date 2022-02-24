package com.idltd.hydramob.entity.address_lists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY_STREETS")
public class Street_List {
    @Id
    @Column(name = "STREET_ID", nullable = false)
    public Long street_id;

    @Column(name = "STREET_NAME", nullable = false)
    public String street_name;

    @Column(name = "STREET_GOOGLE_ID", nullable = false)
    public String street_google_id;

    @Column(name = "STREET_LATITUDE", nullable = false)
    public String street_latitude;

    @Column(name = "STREET_LONGITUDE", nullable = false)
    public String street_longitude;

    @Column(name = "VT_ID", nullable = false)
    public Long vt_id;

    @Column(name = "COUNTRY_ID", nullable = false)
    public Long country_id;

    @Column(name = "AREA_ID", nullable = false)
    public Long area_id;

    @Column(name = "REGION_ID", nullable = false)
    public Long region_id;

    @Column(name = "CITY_ID", nullable = false)
    public Long city_id;
}