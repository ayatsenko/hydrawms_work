package com.idltd.hydramob.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BarGraph {
    @Id
    @Column(name = "ID", nullable = false)
    public Long id;

    @Column(name = "LABELS", nullable = false)
    public String labels;

    @Column(name = "DATA1", nullable = false)
    public Integer data1;

    @Column(name = "DATA2", nullable = false)
    public Integer data2;
}