package com.idltd.hydramob.entity.sale_funnel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class vSaleFunnel {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "LABELS", nullable = false)
    public String labels;

    @Column(name = "DATA1", nullable = false)
    public Integer data1;
}