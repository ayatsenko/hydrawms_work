package com.idltd.hydramob.entity.wms_clients_exchange;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "EXCHANGE_SERVICE_TYPE_FILE", schema="HCOMM")
public class ExchangeServiceTypeFile {
    @Id
    @SequenceGenerator(name = "EXCHANGE_SERVICE_TYPE_FILE_SEQ", sequenceName = "EXCHANGE_SERVICE_TYPE_FILE_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_TYPE_FILE_SEQ")
    @Column(name = "EXCH_SER_FILE_ID", nullable = false)
    public Long exch_ser_file_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "ROLE_ID")
    public Long role_id;

    @Column(name = "EXCH_SER_FILE_NAME")
    public String exch_ser_file_name;

    @Column(name = "EXCH_SER_FILE_DATE")
    public Date exch_ser_file_date;

    @Lob
    @Column(name = "EXCH_SER_FILE_BODY", nullable = false)
    public Blob exch_ser_file_body;

    @Column(name = "EXCH_SER_FILE_CONTENTTYPE")
    public String exch_ser_file_contenttype;

    @Column(name = "EXCH_SER_FILE_INSERT_TYPE")
    public Long exch_ser_file_insert_type;

    @Column(name = "EXCH_SER_TYPE_ID")
    public Long exch_ser_type_id;

    public Long getExch_ser_file_id() {
        return exch_ser_file_id;
    }

    public void setExch_ser_file_id(Long exch_ser_file_id) {
        this.exch_ser_file_id = exch_ser_file_id;
    }

    public Long getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Long cnt_id) {
        this.cnt_id = cnt_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getExch_ser_file_name() {
        return exch_ser_file_name;
    }

    public void setExch_ser_file_name(String exch_ser_file_name) {
        this.exch_ser_file_name = exch_ser_file_name;
    }

    public Date getExch_ser_file_date() {
        return exch_ser_file_date;
    }

    public void setExch_ser_file_date(Date exch_ser_file_date) {
        this.exch_ser_file_date = exch_ser_file_date;
    }

    public Blob getExch_ser_file_body() {
        return exch_ser_file_body;
    }

    public void setExch_ser_file_body(Blob exch_ser_file_body) {
        this.exch_ser_file_body = exch_ser_file_body;
    }

    public String getExch_ser_file_contenttype() {
        return exch_ser_file_contenttype;
    }

    public void setExch_ser_file_contenttype(String exch_ser_file_contenttype) {
        this.exch_ser_file_contenttype = exch_ser_file_contenttype;
    }

    public Long getExch_ser_file_insert_type() {
        return exch_ser_file_insert_type;
    }

    public void setExch_ser_file_insert_type(Long exch_ser_file_insert_type) {
        this.exch_ser_file_insert_type = exch_ser_file_insert_type;
    }

    public Long getExch_ser_type_id() {
        return exch_ser_type_id;
    }

    public void setExch_ser_type_id(Long exch_ser_type_id) {
        this.exch_ser_type_id = exch_ser_type_id;
    }
}