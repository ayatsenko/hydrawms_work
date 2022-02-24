package com.idltd.hydramob.entity.part_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLGClaimPalletsList {
    @Id
    @Column(name = "CLMPL_ID", nullable = false)
    public Long clmpl_id;

    @Column(name = "CLMPL_HEIGHT")
    public Long clmpl_height;

    @Column(name = "CLMPL_WIDTH")
    public Long clmpl_width;

    @Column(name = "CLMPL_LENGHT")
    public Long clmpl_lenght;

    @Column(name = "CLMPL_COUNT")
    public Long clmpl_count;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
