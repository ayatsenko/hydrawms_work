package com.idltd.hydramob.Sheduler.ftp_stock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FTPStockClientParamList {
    @Id
    @Column(name = "EXCH_PARAM_TYPE_ID", nullable = false)
    public Long exch_param_type_id;

    @Column(name = "EXCH_PARAM_LINK_VALUE")
    public String exch_param_link_value;
}
