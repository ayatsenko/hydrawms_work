package com.idltd.hydramob.entity.wms_clients_exchange;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSClientsExchange {
    @Id
    @Column(name = "EXCH_SER_FILE_ID", nullable = false)
    public Long exch_ser_file_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "ROLE_ID")
    public Long role_id;

    @Column(name = "ROLE_NAME")
    public String role_name;

    @Column(name = "EXCH_SER_FILE_NAME")
    public String exch_ser_file_name;

    @Column(name = "EXCH_SER_FILE_DATE")
    public String exch_ser_file_date;

    @Column(name = "EXCH_SER_FILE_CONTENTTYPE")
    public String exch_ser_file_contenttype;

    @Column(name = "EXCH_SER_FILE_INSERT_TYPE")
    public Long exch_ser_file_insert_type;

    @Column(name = "EXCH_SER_TYPE_ID")
    public Long exch_ser_type_id;

    @Column(name = "EXCH_SER_TYPE_NAME")
    public String exch_ser_type_name;
}
