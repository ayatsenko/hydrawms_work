package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientReferenceList {
    @Id
    @Column(name = "CNT_REF_ID", nullable = false)
    public Long cnt_ref_id;

    @Column(name = "CNT_ACCOUNT")
    public String cnt_account;

    @Column(name = "CNT_REF_DEFAULT")
    public Long cnt_ref_default;
}
