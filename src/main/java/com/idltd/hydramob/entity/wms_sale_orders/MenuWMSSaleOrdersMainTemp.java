package com.idltd.hydramob.entity.wms_sale_orders;

import javax.persistence.*;

@Entity
@Table( name = "CLIENT_ORDERS_TEMP")
public class MenuWMSSaleOrdersMainTemp {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

    @Column(name = "DB_USER_ID", nullable = false)
    public Long db_user_id;

    @Column(name = "DB_ROLE_ID", nullable = false)
    public Long db_role_id;

    @Column(name = "ADD_DATE", nullable = false)
    public String add_date;

    @Column(name = "SENDER")
    public String sender;

    @Column(name = "SENDER_CITY")
    public String sender_city;

    @Column(name = "SENDER_BRANCH")
    public String sender_branch;

    @Column(name = "SENDER_PHONE")
    public String sender_phone;

    @Column(name = "RECIVER")
    public String reciver;

    @Column(name = "RECIVER_CITY")
    public String reciver_city;

    @Column(name = "RECIVER_BRANCH")
    public String reciver_branch;

    @Column(name = "RECIVER_PHONE")
    public String reciver_phone;

    @Column(name = "TTN")
    public String ttn;

    @Column(name = "ITEMS")
    public String items;

    @Column(name = "SKU")
    public String sku;

    @Column(name = "COUNTS")
    public String counts;

    @Column(name = "ORDER_ID")
    public String order_id;

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

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public Long getRow_id() {
        return row_id;
    }

    public void setRow_id(Long row_id) {
        this.row_id = row_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender_city() {
        return sender_city;
    }

    public void setSender_city(String sender_city) {
        this.sender_city = sender_city;
    }

    public String getSender_branch() {
        return sender_branch;
    }

    public void setSender_branch(String sender_branch) {
        this.sender_branch = sender_branch;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getReciver_city() {
        return reciver_city;
    }

    public void setReciver_city(String reciver_city) {
        this.reciver_city = reciver_city;
    }

    public String getReciver_branch() {
        return reciver_branch;
    }

    public void setReciver_branch(String reciver_branch) {
        this.reciver_branch = reciver_branch;
    }

    public String getReciver_phone() {
        return reciver_phone;
    }

    public void setReciver_phone(String reciver_phone) {
        this.reciver_phone = reciver_phone;
    }

    public String getTtn() {
        return ttn;
    }

    public void setTtn(String ttn) {
        this.ttn = ttn;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
