package com.idltd.hydramob.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LineGraph {
    @Id
    @Column(name = "ID", nullable = false)
    public Long id;

    @Column(name = "LABELS", nullable = false)
    public String labels;

    @Column(name = "DATA1", nullable = false)
    public Integer data1;

    @Column(name = "DATA2", nullable = false)
    public Integer data2;

    @Column(name = "DATA3", nullable = false)
    public Integer data3;

    @Column(name = "DATA4", nullable = false)
    public Integer data4;

    @Column(name = "DATA5", nullable = false)
    public Integer data5;

    @Column(name = "DATA6", nullable = false)
    public Integer data6;
}