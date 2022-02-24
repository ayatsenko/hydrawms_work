package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientsWHParametersList {
    @Id
    @Column(name = "EXCH_PARAM_LINK_ID", nullable = false)
    public Long exch_param_link_id;

    @Column(name = "EXCH_PARAM_TYPE_ID")
    public Long exch_param_type_id;

    @Column(name = "EXCH_PARAM_TYPE_NAME")
    public String exch_param_type_name;

    @Column(name = "EXCH_PARAM_LINK_VALUE")
    public String exch_param_link_value;
}
