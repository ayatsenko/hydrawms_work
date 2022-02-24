package com.idltd.hydramob.entity.address_lists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY_HOUSES")
public class House_List {
    @Id
    @Column(name = "HOUSE_ID", nullable = false)
    public Long house_id;

    @Column(name = "HOUSE_NAME", nullable = false)
    public String house_name;

    @Column(name = "ZIP_CODE", nullable = false)
    public String zip_code;

    @Column(name = "HOUSE_GOOGLE_ID", nullable = false)
    public String house_google_id;

    @Column(name = "HOUSE_LATITUDE", nullable = false)
    public String house_latitude;

    @Column(name = "HOUSE_LONGITUDE", nullable = false)
    public String house_longitude;

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

    @Column(name = "STREET_ID", nullable = false)
    public Long street_id;
}