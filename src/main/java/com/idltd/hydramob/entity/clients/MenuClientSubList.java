package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientSubList {
    @Id
    @Column(name = "CNT_SUB_ID", nullable = false)
    public Long cnt_sub_id;

    @Column(name = "CNT_SUB_NAME")
    public String cnt_sub_name;
}
