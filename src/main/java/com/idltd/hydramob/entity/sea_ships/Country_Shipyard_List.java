package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY_SHIPYARDS")
public class Country_Shipyard_List {
    @Id
    @Column(name = "SHIPYARD_ID", nullable = false)
    public Long shipyard_id;

    @Column(name = "SHIPYARD_NAME", nullable = false)
    public String shipyard_name;

    @Column(name = "VT_ID", nullable = false)
    public Long vt_id;

    @Column(name = "COUNTRY_ID", nullable = false)
    public Long country_id;
}