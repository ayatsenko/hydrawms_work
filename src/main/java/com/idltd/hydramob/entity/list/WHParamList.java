package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WH_PARAMETERS")
public class WHParamList {
    @Id
    @Column(name = "WH_PARAM_ID", nullable = false)
    public Long wh_param_id;

    @Column(name = "WH_PARAM_NAME")
    public String wh_param_name;

    @Column(name = "WH_PARAM_SNAME")
    public String wh_param_sname;

    @Column(name = "WH_PARAM_DESCRIPTION")
    public String wh_param_description;

    @Column(name = "CURRENCY_ID")
    public Long currency_id;

    @Column(name = "WH_PARAM_RATE")
    public String wh_param_rate;

    @Column(name = "WH_PARAM_DEFAULT")
    public Long wh_param_default;
}
