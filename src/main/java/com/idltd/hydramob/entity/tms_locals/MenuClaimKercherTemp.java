package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimKercherTemp {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

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
}
