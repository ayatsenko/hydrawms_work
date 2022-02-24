package com.idltd.hydramob.entity.country_shipyards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuCountryShipyardsMain {
    @Id
    @Column(name = "SHIPYARD_ID", nullable = false)
    public Long shipyard_id;

    @Column(name = "SHIPYARD_NAME")
    public String shipyard_name;

    @Column(name = "VT_ID")
    public Long vt_id;

    @Column(name = "SHIPYARD_NAMERUS")
    public String shipyard_namerus;

    @Column(name = "SHIPYARD_CODE")
    public String shipyard_code;

    @Column(name = "COUNTRY_ID")
    public Long country_id;

    @Column(name = "COUNTRY_NAME")
    public String country_name;
}
