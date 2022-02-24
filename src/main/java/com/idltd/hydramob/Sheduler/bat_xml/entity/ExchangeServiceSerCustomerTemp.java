package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_SERVICE_SER_TEMP", schema = "HCOMM")
public class ExchangeServiceSerCustomerTemp {
    @Column(name = "EXCH_SER_FILE_ID")
    public Long exch_ser_file_id;

    @Id
    @Column(name = "EXCH_SER_SER_ROW_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_SER_TEMP_SEQ", sequenceName = "EXCHANGE_SERVICE_SER_TEMP_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_SER_TEMP_SEQ")
    public Long exch_ser_skh_row_id;

    @Column(name = "RECTYP")
    public String rectyp;

    @Column(name = "PROD_NR")
    public String prod_nr;

    @Column(name = "SERIAL_NR")
    public String serial_nr;

    @Column(name = "DATE_PROD")
    public String date_prod;

    @Column(name = "DATE_EXPIRE")
    public String date_expire;

    @Column(name = "ONE_C_ID")
    public String one_c_id;

    public Long getExch_ser_file_id() {
        return exch_ser_file_id;
    }

    public void setExch_ser_file_id(Long exch_ser_file_id) {
        this.exch_ser_file_id = exch_ser_file_id;
    }

    public Long getExch_ser_skh_row_id() {
        return exch_ser_skh_row_id;
    }

    public void setExch_ser_skh_row_id(Long exch_ser_skh_row_id) {
        this.exch_ser_skh_row_id = exch_ser_skh_row_id;
    }

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getProd_nr() {
        return prod_nr;
    }

    public void setProd_nr(String prod_nr) {
        this.prod_nr = prod_nr;
    }

    public String getSerial_nr() {
        return serial_nr;
    }

    public void setSerial_nr(String serial_nr) {
        this.serial_nr = serial_nr;
    }

    public String getDate_prod() {
        return date_prod;
    }

    public void setDate_prod(String date_prod) {
        this.date_prod = date_prod;
    }

    public String getDate_expire() {
        return date_expire;
    }

    public void setDate_expire(String date_expire) {
        this.date_expire = date_expire;
    }

    public String getOne_c_id() {
        return one_c_id;
    }

    public void setOne_c_id(String one_c_id) {
        this.one_c_id = one_c_id;
    }
}
