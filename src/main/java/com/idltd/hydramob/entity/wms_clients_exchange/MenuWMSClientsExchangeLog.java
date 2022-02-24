package com.idltd.hydramob.entity.wms_clients_exchange;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSClientsExchangeLog {
    @Id
    @Column(name = "EXCH_SER_FILE_LOG_ID", nullable = false)
    public Long exch_ser_file_log_id;

    @Column(name = "EXCH_SER_FILE_LOG_TYPE_ID", nullable = false)
    public Long exch_ser_file_log_type_id;

    @Column(name = "EXCH_SER_FILE_LOG_TYPE_NAME")
    public String exch_ser_file_log_type_name;

    @Column(name = "EXCH_SER_FILE_LOG_ADD_DATE")
    public String exch_ser_file_log_add_date;

    @Column(name = "EXCH_SER_FILE_LOG_START_DATE")
    public String exch_ser_file_log_start_date;

    @Column(name = "EXCH_SER_FILE_LOG_END_DATE")
    public String exch_ser_file_log_end_date;

    @Column(name = "EXCH_SER_FILE_LOG_STATUS_ID")
    public Long exch_ser_file_log_status_id;

    @Column(name = "EXCH_SER_FILE_LOG_STATUS_SNAME")
    public String exch_ser_file_log_status_sname;

    @Column(name = "EXCH_SER_FILE_LOG_STATUS_COLOUR")
    public String exch_ser_file_log_status_colour;
}
