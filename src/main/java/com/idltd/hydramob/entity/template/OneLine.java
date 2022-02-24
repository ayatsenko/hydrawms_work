package com.idltd.hydramob.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OneLine {
    @Id
    @Column(name = "LABELS", nullable = false)
    public String labels;
}