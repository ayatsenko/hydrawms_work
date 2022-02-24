package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PALLET_TYPE")
public class PalletList {
    @Id
    public Long pal_type_id;

    @Column(name = "PAL_TYPE_NAME", nullable = false)
    public String pal_type_name;

    @Column(name = "PAL_TYPE_HEIGHT", nullable = false)
    public String pal_type_height;

    @Column(name = "PAL_TYPE_WIDTH", nullable = false)
    public String pal_type_width;

    @Column(name = "PAL_TYPE_LEIGHT", nullable = false)
    public String pal_type_leight;
}