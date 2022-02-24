package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
public class DistinctTypeahead {
    @Id
    @Column(name = "SP_ID")
    public Long id;


    @Column(name = "SP_SHIPPER")
    public String name;
}
