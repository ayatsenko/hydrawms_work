package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_FLD_HUSQVARNA_TEMP")
public class FLDClaimHusqvarnaTemp {
    @Id
    public Long row_id;

    @Column(name = "DB_USER_ID", nullable = false)
    public Long db_user_id;

    @Column(name = "DB_ROLE_ID", nullable = false)
    public Long db_role_id;

    @Column(name = "DOC_NUM")
    public String doc_num;

    @Column(name = "ORDER_NUM")
    public String order_num;

    @Column(name = "COMPANY_NUM")
    public String company_num;

    @Column(name = "ORDER_DATE")
    public String order_date;

    @Column(name = "RECIVER_TAX_NUM")
    public String reciver_tax_num;

    @Column(name = "CLIENT_NAME")
    public String client_name;

    @Column(name = "TRANSPORT")
    public String transport;

    @Column(name = "TTN_NUMBER")
    public String ttn_number;

    @Column(name = "CAR_NUM")
    public String car_num;

    @Column(name = "DRIVER")
    public String driver;

    @Column(name = "REGISTRATION_TIME")
    public String registration_time;

    @Column(name = "START_TIME")
    public String start_time;

    @Column(name = "STOP_TIME")
    public String stop_time;

    @Column(name = "LOAD_PLAN_DATE")
    public String load_plan_date;

    @Column(name = "LOAD_FACT_DATE")
    public String load_fact_date;

    @Column(name = "LOADED_PLAN_DATE")
    public String loaded_plan_date;

    @Column(name = "LOADED_FACT_DATE")
    public String loaded_fact_date;

    @Column(name = "PALLET_FACT_COUNT")
    public String pallet_fact_count;

    @Column(name = "BOX_FACT_COUNT")
    public String box_fact_count;

    @Column(name = "PACK_TYPE")
    public String pack_type;

    @Column(name = "WH")
    public String wh;

    @Column(name = "STRING_COUNT")
    public String string_count;

    @Column(name = "GOODS_STATUS")
    public String goods_status;

    @Column(name = "AMMOUNT")
    public String ammount;

    @Column(name = "WEIGHT")
    public String weight;

    @Column(name = "DOC_SUM")
    public String doc_sum;

    @Column(name = "LOADED_CITY")
    public String loaded_city;

    @Column(name = "LOADED_ADDRESS")
    public String loaded_address;

    @Column(name = "CONTACT")
    public String contact;

    @Column(name = "DOC_RETURN")
    public String doc_return;

    @Column(name = "WH_NOTES")
    public String wh_notes;

    @Column(name = "TRANS_NOTES")
    public String trans_notes;

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

    public String getDoc_num() {
        return doc_num;
    }

    public void setDoc_num(String doc_num) {
        this.doc_num = doc_num;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getCompany_num() {
        return company_num;
    }

    public void setCompany_num(String company_num) {
        this.company_num = company_num;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getReciver_tax_num() {
        return reciver_tax_num;
    }

    public void setReciver_tax_num(String reciver_tax_num) {
        this.reciver_tax_num = reciver_tax_num;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTtn_number() {
        return ttn_number;
    }

    public void setTtn_number(String ttn_number) {
        this.ttn_number = ttn_number;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(String registration_time) {
        this.registration_time = registration_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getLoad_plan_date() {
        return load_plan_date;
    }

    public void setLoad_plan_date(String load_plan_date) {
        this.load_plan_date = load_plan_date;
    }

    public String getLoad_fact_date() {
        return load_fact_date;
    }

    public void setLoad_fact_date(String load_fact_date) {
        this.load_fact_date = load_fact_date;
    }

    public String getLoaded_plan_date() {
        return loaded_plan_date;
    }

    public void setLoaded_plan_date(String loaded_plan_date) {
        this.loaded_plan_date = loaded_plan_date;
    }

    public String getLoaded_fact_date() {
        return loaded_fact_date;
    }

    public void setLoaded_fact_date(String loaded_fact_date) {
        this.loaded_fact_date = loaded_fact_date;
    }

    public String getPallet_fact_count() {
        return pallet_fact_count;
    }

    public void setPallet_fact_count(String pallet_fact_count) {
        this.pallet_fact_count = pallet_fact_count;
    }

    public String getBox_fact_count() {
        return box_fact_count;
    }

    public void setBox_fact_count(String box_fact_count) {
        this.box_fact_count = box_fact_count;
    }

    public String getPack_type() {
        return pack_type;
    }

    public void setPack_type(String pack_type) {
        this.pack_type = pack_type;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }

    public String getString_count() {
        return string_count;
    }

    public void setString_count(String string_count) {
        this.string_count = string_count;
    }

    public String getGoods_status() {
        return goods_status;
    }

    public void setGoods_status(String goods_status) {
        this.goods_status = goods_status;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDoc_sum() {
        return doc_sum;
    }

    public void setDoc_sum(String doc_sum) {
        this.doc_sum = doc_sum;
    }

    public String getLoaded_city() {
        return loaded_city;
    }

    public void setLoaded_city(String loaded_city) {
        this.loaded_city = loaded_city;
    }

    public String getLoaded_address() {
        return loaded_address;
    }

    public void setLoaded_address(String loaded_address) {
        this.loaded_address = loaded_address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDoc_return() {
        return doc_return;
    }

    public void setDoc_return(String doc_return) {
        this.doc_return = doc_return;
    }

    public String getWh_notes() {
        return wh_notes;
    }

    public void setWh_notes(String wh_notes) {
        this.wh_notes = wh_notes;
    }

    public String getTrans_notes() {
        return trans_notes;
    }

    public void setTrans_notes(String trans_notes) {
        this.trans_notes = trans_notes;
    }
}