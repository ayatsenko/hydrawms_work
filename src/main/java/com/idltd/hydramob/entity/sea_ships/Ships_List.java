package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAGENT_SHIPS")
public class Ships_List {
    @Id
    @Column(name = "SHIP_ID", nullable = false)
    public Long ship_id;

    @Column(name = "SHIP_NAME", nullable = false)
    public String ship_name;

    @Column(name = "VT_ID", nullable = false)
    public Long vt_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;
}