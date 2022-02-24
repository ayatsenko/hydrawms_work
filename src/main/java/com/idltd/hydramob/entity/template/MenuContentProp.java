package com.idltd.hydramob.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuContentProp {
    @Id
    @Column(name = "MC_INT_NAME", nullable = false)
    public String mc_int_name;

    @Column(name = "MC_HIDE", nullable = false)
    public Integer hidden;

    @Column(name = "MC_LIST_NUM", nullable = false)
    public Integer order_num;
}