package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_FLD_KERCHER_TEMP")
public class FLDClaimKercherTemp {
    @Id
    public Long row_id;

    @Column(name = "DB_USER_ID", nullable = false)
    public Long db_user_id;

    @Column(name = "DB_ROLE_ID", nullable = false)
    public Long db_role_id;

    @Column(name = "ORDER_DATE")
    public String order_date;

    @Column(name = "ORDER_NUMBER")
    public String order_number;

    @Column(name = "CLIENT_NAME")
    public String client_name;

    @Column(name = "DELIVERY_ADDRESS")
    public String delivery_address;

    @Column(name = "CONTACT_PERSON")
    public String contact_person;

    @Column(name = "LOAD_DATE")
    public String load_date;

    @Column(name = "UNLOAD_DATE")
    public String unload_date;

    @Column(name = "COMMENT_TEXT")
    public String comment_text;

    @Column(name = "TTN_NUMBER")
    public String ttn_number;

    @Column(name = "CAR_NUMBER")
    public String car_number;

    @Column(name = "DRIVER_NAME")
    public String driver_name;

    @Column(name = "STATUS_NAME")
    public String status_name;

    @Column(name = "MANAGER_NAME")
    public String manager_name;

    @Column(name = "CLOSE_DATE")
    public String close_date;

    @Column(name = "DOC_SUM")
    public String doc_sum;

    public Long getRow_id() {
        return row_id;
    }

    public void setRow_id(Long row_id) {
        this.row_id = row_id;
    }

    public Long getDb_user_id() {
        return db_user_id;
    }

    public void setDb_user_id(Long db_user_id) {
        this.db_user_id = db_user_id;
    }

    public Long getDb_role_id() {
        return db_role_id;
    }

    public void setDb_role_id(Long db_role_id) {
        this.db_role_id = db_role_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getLoad_date() {
        return load_date;
    }

    public void setLoad_date(String load_date) {
        this.load_date = load_date;
    }

    public String getUnload_date() {
        return unload_date;
    }

    public void setUnload_date(String unload_date) {
        this.unload_date = unload_date;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getTtn_number() {
        return ttn_number;
    }

    public void setTtn_number(String ttn_number) {
        this.ttn_number = ttn_number;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getDoc_sum() {
        return doc_sum;
    }

    public void setDoc_sum(String doc_sum) {
        this.doc_sum = doc_sum;
    }
}
