package com.idltd.hydramob.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FiveLineBarGraph {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "LABELS", nullable = false)
    public String labels;

    @Column(name = "DATA1", nullable = false)
    public Long data1;

    @Column(name = "DATA2", nullable = false)
    public Long data2;

    @Column(name = "DATA3", nullable = false)
    public Long data3;

    @Column(name = "DATA4", nullable = false)
    public Long data4;

    @Column(name = "DATA5", nullable = false)
    public Long data5;
}