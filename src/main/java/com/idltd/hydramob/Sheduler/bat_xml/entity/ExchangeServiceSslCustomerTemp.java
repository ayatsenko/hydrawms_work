package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_SERVICE_SSL_TEMP", schema = "HCOMM")
public class ExchangeServiceSslCustomerTemp {
    @Column(name = "EXCH_SER_FILE_ID")
    public Long exch_ser_file_id;

    @Id
    @Column(name = "EXCH_SER_SSL_ROW_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_SSL_TEMP_SEQ", sequenceName = "EXCHANGE_SERVICE_SSL_TEMP_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_SSL_TEMP_SEQ")
    public Long exch_ser_ssl_row_id;

    @Column(name = "RECTYP")
    public String rectyp;

    @Column(name = "FIRM_NR")
    public String firm_nr;

    @Column(name = "ORDER_NR")
    public String order_nr;

    @Column(name = "CUSTOMER_NR")
    public String customer_nr;

    @Column(name = "DATE_DELIV")
    public String date_deliv;

    @Column(name = "ADR_STREET")
    public String adr_street;

    @Column(name = "ADR_CITY")
    public String adr_city;

    @Column(name = "C_ORDER_NR")
    public String c_order_nr;

    @Column(name = "WH_NR")
    public String wh_nr;

    @Column(name = "DATE_CREATED")
    public String date_created;

    @Column(name = "DATE_ORDERED")
    public String date_ordered;

    @Column(name = "INFO_1")
    public String info_1;

    @Column(name = "ADR_ZIPCODE")
    public String adr_zipcode;

    @Column(name = "ADR_POSTBOX")
    public String adr_postbox;

    @Column(name = "ADR_COUNTRY_CODE")
    public String adr_country_code;

    public Long getExch_ser_file_id() {
        return exch_ser_file_id;
    }

    public void setExch_ser_file_id(Long exch_ser_file_id) {
        this.exch_ser_file_id = exch_ser_file_id;
    }

    public Long getExch_ser_ssl_row_id() {
        return exch_ser_ssl_row_id;
    }

    public void setExch_ser_ssl_row_id(Long exch_ser_ssl_row_id) {
        this.exch_ser_ssl_row_id = exch_ser_ssl_row_id;
    }

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getFirm_nr() {
        return firm_nr;
    }

    public void setFirm_nr(String firm_nr) {
        this.firm_nr = firm_nr;
    }

    public String getOrder_nr() {
        return order_nr;
    }

    public void setOrder_nr(String order_nr) {
        this.order_nr = order_nr;
    }

    public String getCustomer_nr() {
        return customer_nr;
    }

    public void setCustomer_nr(String customer_nr) {
        this.customer_nr = customer_nr;
    }

    public String getDate_deliv() {
        return date_deliv;
    }

    public void setDate_deliv(String date_deliv) {
        this.date_deliv = date_deliv;
    }

    public String getAdr_street() {
        return adr_street;
    }

    public void setAdr_street(String adr_street) {
        this.adr_street = adr_street;
    }

    public String getAdr_city() {
        return adr_city;
    }

    public void setAdr_city(String adr_city) {
        this.adr_city = adr_city;
    }

    public String getC_order_nr() {
        return c_order_nr;
    }

    public void setC_order_nr(String c_order_nr) {
        this.c_order_nr = c_order_nr;
    }

    public String getWh_nr() {
        return wh_nr;
    }

    public void setWh_nr(String wh_nr) {
        this.wh_nr = wh_nr;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_ordered() {
        return date_ordered;
    }

    public void setDate_ordered(String date_ordered) {
        this.date_ordered = date_ordered;
    }

    public String getInfo_1() {
        return info_1;
    }

    public void setInfo_1(String info_1) {
        this.info_1 = info_1;
    }

    public String getAdr_zipcode() {
        return adr_zipcode;
    }

    public void setAdr_zipcode(String adr_zipcode) {
        this.adr_zipcode = adr_zipcode;
    }

    public String getAdr_postbox() {
        return adr_postbox;
    }

    public void setAdr_postbox(String adr_postbox) {
        this.adr_postbox = adr_postbox;
    }

    public String getAdr_country_code() {
        return adr_country_code;
    }

    public void setAdr_country_code(String adr_country_code) {
        this.adr_country_code = adr_country_code;
    }
}
