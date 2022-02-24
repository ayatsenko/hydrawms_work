package com.idltd.hydramob.entity.kpi_facts.cs_crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIFactsCsCRMGeneral {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "PARAM_ID")
    public Long param_id;

    @Column(name = "PARAM_NAME")
    public String param_name;

    @Column(name = "PARAM_VALUE")
    public String param_value;
}
