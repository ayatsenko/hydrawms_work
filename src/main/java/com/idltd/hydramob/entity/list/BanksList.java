package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BanksList {
    @Id
    public Long bank_id;

    @Column(name = "BANK_NAME", nullable = false)
    public String bank_name;
}