package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTransportCountryList {
    @Id
    @Column(name = "CNTCL_ID", nullable = false)
    public Long cntcl_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "COUNTRY_ID", nullable = false)
    public Long country_id;

    @Column(name = "COUNTRY_SNAME")
    public String country_sname;

    @Column(name = "CNTCL_DATE")
    public String cntcl_date;
}
