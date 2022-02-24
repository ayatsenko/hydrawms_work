package com.idltd.hydramob.Sheduler.ukr_post.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UKR_POST_ADDRESS_TEMP")
public class UkrPostAddressTemp {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Integer row_id;

    @JsonProperty(value="Область")
    @Column(name = "AREA", nullable = false)
    public String area;

    @JsonProperty(value="Район")
    @Column(name = "REGION", nullable = false)
    public String region;

    @JsonProperty(value="Населений пункт")
    @Column(name = "CITY", nullable = false)
    public String city;

    @JsonProperty(value="індекс НП")
    @Column(name = "ZIP_CODE")
    public String zip_code;

    @JsonProperty(value="Назва вулиці")
    @Column(name = "STREET")
    public String street;

    @JsonProperty(value="№ будинку")
    @Column(name = "HOUSES")
    public String houses;

    @JsonProperty(value="Особливості функціонування ВПЗ")
    @Column(name = "SPECIAL")
    public String special;
}
