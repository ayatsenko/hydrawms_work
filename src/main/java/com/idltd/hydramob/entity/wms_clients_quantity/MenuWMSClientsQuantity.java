package com.idltd.hydramob.entity.wms_clients_quantity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSClientsQuantity {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

    @Column(name = "CLIENT_NAME", nullable = false)
    public String client_name;

    @Column(name = "PRODUCT_NAME")
    public String product_name;

    @Column(name = "PRODUCT_QUANTITY")
    public String product_quantity;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
