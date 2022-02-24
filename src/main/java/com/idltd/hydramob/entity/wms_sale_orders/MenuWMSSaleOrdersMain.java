package com.idltd.hydramob.entity.wms_sale_orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSSaleOrdersMain {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

    @Column(name = "EXCH_SER_FILE_ID", nullable = false)
    public Long exch_ser_file_id;

    @Column(name = "EXCH_SER_FILE_ROW_ID", nullable = false)
    public Long exch_ser_file_row_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CLIENT_ID")
    public String client_id;

    @Column(name = "ORDER_DATE")
    public String order_date;

    @Column(name = "ORDER_NUM")
    public String order_num;

    @Column(name = "QUANTITY")
    public String quantity;

    @Column(name = "SHIPPING")
    public String shipping;

    @Column(name = "INSTRUCTIONS")
    public String instructions;

    @Column(name = "RECIVER_NAME")
    public String reciver_name;

    @Column(name = "RECIVER_TEL_NUM")
    public String reciver_tel_num;

    @Column(name = "RECIVER_EMAIL")
    public String reciver_email;

    @Column(name = "RECIVER_ADDRESS_1")
    public String reciver_address_1;

    @Column(name = "RECIVER_ADDRESS_2")
    public String reciver_address_2;

    @Column(name = "RECIVER_CITY")
    public String reciver_city;

    @Column(name = "RECIVER_STATE")
    public String reciver_state;

    @Column(name = "RECIVER_ZIP_CODE")
    public String reciver_zip_code;

    @Column(name = "RECIVER_ZIP_COUNTRY")
    public String reciver_zip_country;

    @Column(name = "EXCH_SALE_ORDER_POST_ID")
    public String exch_sale_order_post_id;
}
