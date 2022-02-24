package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailClientReferenceList {
    @Id
    @Column(name = "CNT_REF_ID", nullable = false)
    public Long cnt_ref_id;

    @Column(name = "BANK_ID")
    public Long bank_id;

    @Column(name = "CNT_IPN")
    public Long cnt_ipn;

    @Column(name = "CNT_IBAN")
    public String cnt_iban;

    @Column(name = "CNT_ACCOUNT")
    public String cnt_account;

    @Column(name = "CNT_REF_DEFAULT")
    public Long cnt_ref_default;
}
