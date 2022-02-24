package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientsWHParametersTypesList {
    @Id
    @Column(name = "EXCH_PARAM_TYPE_ID")
    public Long exch_param_type_id;

    @Column(name = "EXCH_PARAM_TYPE_NAME")
    public String exch_param_type_name;

    @Column(name = "EXCH_PARAM_TYPE_SNAME")
    public String exch_param_type_sname;

    @Column(name = "EXCH_PARAM_TYPE_DESCRIPTION")
    public String exch_param_type_description;
}
