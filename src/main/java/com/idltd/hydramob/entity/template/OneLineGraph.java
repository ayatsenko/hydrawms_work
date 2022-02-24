package com.idltd.hydramob.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OneLineGraph {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "LABELS", nullable = false)
    public String labels;

    @Column(name = "DATA1", nullable = false)
    public Integer data1;
}