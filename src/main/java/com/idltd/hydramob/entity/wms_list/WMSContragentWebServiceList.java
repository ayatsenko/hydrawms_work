package com.idltd.hydramob.entity.wms_list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WMSContragentWebServiceList {
    @Id
    @Column(name = "EXCH_SER_TYPE_ID", nullable = false)
    public Long exch_ser_type_id;

    @Column(name = "EXCH_SER_TYPE_NAME", nullable = false)
    public String exch_ser_type_name;

    @Column(name = "EXCH_SER_TYPE_SNAME")
    public String exch_ser_type_sname;

    @Column(name = "EXCH_SER_TYPE_DESCRIPTION")
    public String exch_ser_type_description;

    @Column(name = "EXCH_SER_WEB_TYPE_ID", nullable = false)
    public Long exch_ser_web_type_id;
}