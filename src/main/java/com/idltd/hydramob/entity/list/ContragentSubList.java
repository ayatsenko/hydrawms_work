package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContragentSubList {
    @Id
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;
}