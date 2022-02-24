package com.idltd.hydramob.entity.wms_list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WMSUsersPrintersList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "PRINTER_NAME", nullable = false)
    public String printer_name;
}