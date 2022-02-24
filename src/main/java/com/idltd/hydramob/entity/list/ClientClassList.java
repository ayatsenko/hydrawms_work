package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAGENT_CLASS")
public class ClientClassList {
    @Id
    public Long cnt_class_id;

    @Column(name = "CNT_CLASS_NAME", nullable = false)
    public String cnt_class_name;

    @Column(name = "CNT_CLASS_SNAME", nullable = false)
    public String cnt_class_sname;

    @Column(name = "CNT_CLASS_DESCRIPTION", nullable = false)
    public String cnt_class_description;

    @Column(name = "CNT_CLASS_COLOR", nullable = false)
    public String cnt_class_color;
}