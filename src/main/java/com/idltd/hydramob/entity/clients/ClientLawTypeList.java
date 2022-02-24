package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAGENT_LAW_TYPES")
public class ClientLawTypeList {
    @Id
    @Column(name = "cnt_law_type_id", nullable = false)
    public Long cnt_law_type_id;

    @Column(name = "CNT_LAW_TYPE_NAME", nullable = false)
    public String cnt_law_type_name;

    @Column(name = "CNT_LAW_TYPE_SNAME", nullable = false)
    public String cnt_law_type_sname;

    @Column(name = "CNT_LAW_TYPE_DESCRIPTION", nullable = false)
    public String cnt_law_type_description;

    @Column(name = "CNT_LAW_TYPE_COLOR", nullable = false)
    public String cnt_law_type_color;
}