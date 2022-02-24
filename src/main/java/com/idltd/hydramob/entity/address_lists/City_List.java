package com.idltd.hydramob.entity.address_lists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY_CITIES")
public class City_List {
    @Id
    @Column(name = "CITY_ID", nullable = false)
    public Long city_id;

    @Column(name = "CITY_NAME", nullable = false)
    public String city_name;

    @Column(name = "CITY_GOOGLE_ID", nullable = false)
    public String city_google_id;

    @Column(name = "CITY_LATITUDE", nullable = false)
    public String city_latitude;

    @Column(name = "CITY_LONGITUDE", nullable = false)
    public String city_longitude;

    @Column(name = "VT_ID", nullable = false)
    public Long vt_id;

    @Column(name = "COUNTRY_ID", nullable = false)
    public Long country_id;

    @Column(name = "AREA_ID", nullable = false)
    public Long area_id;

    @Column(name = "REGION_ID", nullable = false)
    public Long region_id;
}