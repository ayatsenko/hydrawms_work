package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY")
public class Currency_Use_List {
    @Id
    public Long currency_id;

    @Column(name = "CURRENCY_SNAME", nullable = false)
    public String currency_sname;

    @Column(name = "CURRENCY_NAME", nullable = false)
    public String currency_name;

    @Column(name = "ELECTRONCODE", nullable = false)
    public String electroncode;

    @Column(name = "SYMBOLCODE", nullable = false)
    public String symbolcode;

    @Column(name = "CURRENCY_USE_ID", nullable = false)
    public String currency_use_id;
}