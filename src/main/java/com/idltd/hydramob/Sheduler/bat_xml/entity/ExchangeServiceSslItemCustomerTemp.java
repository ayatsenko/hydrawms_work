package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_SERVICE_SSL_ITEMS_TEMP", schema = "HCOMM")
public class ExchangeServiceSslItemCustomerTemp {
    @Column(name = "EXCH_SER_FILE_ID")
    public Long exch_ser_file_id;

    @Column(name = "EXCH_SER_SSL_ROW_ID")
    public Long exch_ser_ssl_row_id;

    @Id
    @Column(name = "EXCH_SER_SSL_ITEM_ROW_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_SSL_ITEMS_SEQ", sequenceName = "EXCHANGE_SERVICE_SSL_ITEMS_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_SSL_ITEMS_SEQ")
    public Long exch_ser_ssl_item_row_id;

    @Column(name = "RECTYP")
    public String rectyp;

    @Column(name = "ORDER_NR")
    public String order_nr;

    @Column(name = "POSITION_NR")
    public String position_nr;

    @Column(name = "PRODUCT_NR")
    public String product_nr;

    @Column(name = "PROD_SERIAL_NR")
    public String prod_serial_nr;

    @Column(name = "STATUS_QUALITY")
    public String status_quality;

    @Column(name = "BU_QUANTITY")
    public String bu_quantity;

    @Column(name = "DATE_CREATED")
    public String date_created;

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

    public Long getExch_ser_ssl_item_row_id() {
        return exch_ser_ssl_item_row_id;
    }

    public void setExch_ser_ssl_item_row_id(Long exch_ser_ssl_item_row_id) {
        this.exch_ser_ssl_item_row_id = exch_ser_ssl_item_row_id;
    }

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getOrder_nr() {
        return order_nr;
    }

    public void setOrder_nr(String order_nr) {
        this.order_nr = order_nr;
    }

    public String getPosition_nr() {
        return position_nr;
    }

    public void setPosition_nr(String position_nr) {
        this.position_nr = position_nr;
    }

    public String getProduct_nr() {
        return product_nr;
    }

    public void setProduct_nr(String product_nr) {
        this.product_nr = product_nr;
    }

    public String getProd_serial_nr() {
        return prod_serial_nr;
    }

    public void setProd_serial_nr(String prod_serial_nr) {
        this.prod_serial_nr = prod_serial_nr;
    }

    public String getStatus_quality() {
        return status_quality;
    }

    public void setStatus_quality(String status_quality) {
        this.status_quality = status_quality;
    }

    public String getBu_quantity() {
        return bu_quantity;
    }

    public void setBu_quantity(String bu_quantity) {
        this.bu_quantity = bu_quantity;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
