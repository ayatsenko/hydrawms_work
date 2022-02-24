package com.idltd.hydramob.entity.part_world_old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ListClaimTransWays {
    @Id
    @Column(name = "CLMWL_ID", nullable = false)
    public Long clmwl_id;

    @Column(name = "CLMWL_FULLNAME")
    public String clmwl_fullname;
}
