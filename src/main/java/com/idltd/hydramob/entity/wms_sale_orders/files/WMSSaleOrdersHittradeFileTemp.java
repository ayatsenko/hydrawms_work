package com.idltd.hydramob.entity.wms_sale_orders.files;

import javax.persistence.*;

@Entity
@Table( name = "EXCHANGE_SERVICE_TYPE_FILE_HITTRADE", schema = "HCOMM")
public class WMSSaleOrdersHittradeFileTemp {
    @Id
    @Column(name = "EXCH_SER_FILE_HITTRADE_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_TYPE_FIL6_SEQ", sequenceName = "EXCHANGE_SERVICE_TYPE_FIL6_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_TYPE_FIL6_SEQ")
    public Long exch_ser_file_hittrade_id;

    @Column(name = "EXCH_SER_FILE_ID", nullable = false)
    public Long exch_ser_file_id;

    @Column(name = "EXCH_SER_FILE_ROW_ID", nullable = false)
    public Long exch_ser_file_row_id;

    @Column(name = "DB_USER_ID", nullable = false)
    public Long db_user_id;

    @Column(name = "DB_ROLE_ID", nullable = false)
    public Long db_role_id;

    @Column(name = "ADD_DATE", nullable = false)
    public String add_date;

    @Column(name = "POSITION_DATE")
    public String position_date;

    @Column(name = "ORDER_NUM")
    public String order_num;

    @Column(name = "SYSTEM_NUM")
    public String system_num;

    @Column(name = "OUT_ORDER_NUM")
    public String out_order_num;

    @Column(name = "PAID_TYPE")
    public String paid_type;

    @Column(name = "NOMENCLATURE_ID")
    public String nomenclature_id;

    @Column(name = "NOMENCLATURE_NAME")
    public String nomenclature_name;

    @Column(name = "POSITION_COUNT")
    public String position_count;

    @Column(name = "PRICE")
    public String price;

    @Column(name = "UNIT")
    public String unit;

    @Column(name = "TTN")
    public String ttn;

    public Long getExch_ser_file_hittrade_id() {
        return exch_ser_file_hittrade_id;
    }

    public void setExch_ser_file_hittrade_id(Long exch_ser_file_hittrade_id) {
        this.exch_ser_file_hittrade_id = exch_ser_file_hittrade_id;
    }

    public Long getExch_ser_file_id() {
        return exch_ser_file_id;
    }

    public void setExch_ser_file_id(Long exch_ser_file_id) {
        this.exch_ser_file_id = exch_ser_file_id;
    }

    public Long getExch_ser_file_row_id() {
        return exch_ser_file_row_id;
    }

    public void setExch_ser_file_row_id(Long exch_ser_file_row_id) {
        this.exch_ser_file_row_id = exch_ser_file_row_id;
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

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getPosition_date() {
        return position_date;
    }

    public void setPosition_date(String position_date) {
        this.position_date = position_date;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getSystem_num() {
        return system_num;
    }

    public void setSystem_num(String system_num) {
        this.system_num = system_num;
    }

    public String getOut_order_num() {
        return out_order_num;
    }

    public void setOut_order_num(String out_order_num) {
        this.out_order_num = out_order_num;
    }

    public String getPaid_type() {
        return paid_type;
    }

    public void setPaid_type(String paid_type) {
        this.paid_type = paid_type;
    }

    public String getNomenclature_id() {
        return nomenclature_id;
    }

    public void setNomenclature_id(String nomenclature_id) {
        this.nomenclature_id = nomenclature_id;
    }

    public String getNomenclature_name() {
        return nomenclature_name;
    }

    public void setNomenclature_name(String nomenclature_name) {
        this.nomenclature_name = nomenclature_name;
    }

    public String getPosition_count() {
        return position_count;
    }

    public void setPosition_count(String position_count) {
        this.position_count = position_count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTtn() {
        return ttn;
    }

    public void setTtn(String ttn) {
        this.ttn = ttn;
    }
}
