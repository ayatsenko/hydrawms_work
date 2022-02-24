package com.idltd.hydramob.entity.wms_sale_orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSSaleOrdersPost {
    @Id
    @Column(name = "EXCH_SALE_ORDER_POST_DETAIL_ROW_ID", nullable = false)
    public Long exch_sale_order_post_detail_row_id;

    @Column(name = "EXCH_SALE_ORDER_POST_DETAIL_STATUS_DATE")
    public String exch_sale_order_post_detail_status_date;

    @Column(name = "EXCH_SALE_ORDER_POST_DETAIL_STATUS_NAME")
    public String exch_sale_order_post_detail_status_name;
}
