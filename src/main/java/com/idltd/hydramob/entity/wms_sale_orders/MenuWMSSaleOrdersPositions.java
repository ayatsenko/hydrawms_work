package com.idltd.hydramob.entity.wms_sale_orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSSaleOrdersPositions {
    @Id
    @Column(name = "EXCH_SER_FILE_ROW_ID", nullable = false)
    public Long exch_ser_file_row_id;

    @Column(name = "ITEM_ID")
    public String item_id;

    @Column(name = "ITEM_TITLE")
    public String item_title;

    @Column(name = "SKU")
    public String sku;

    @Column(name = "QUANTITY")
    public String quantity;
}
