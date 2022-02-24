package com.idltd.hydramob.Sheduler.ftp_stock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FTPStockClientList {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;
}
