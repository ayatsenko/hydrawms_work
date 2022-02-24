package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTransportCountryList {
    @Id
    @Column(name = "CNTCL_ID", nullable = false)
    public Long cntcl_id;

    @Column(name = "COUNTRY_SNAME")
    public String country_sname;

    @Column(name = "CNTCL_DATE")
    public String cntcl_date;
}
