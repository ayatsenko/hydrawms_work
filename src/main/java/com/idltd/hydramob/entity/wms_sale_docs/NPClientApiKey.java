package com.idltd.hydramob.entity.wms_sale_docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NPClientApiKey {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "API_KEY")
    public String api_key;
}
