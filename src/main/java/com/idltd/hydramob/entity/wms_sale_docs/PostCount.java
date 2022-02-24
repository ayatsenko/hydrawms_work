package com.idltd.hydramob.entity.wms_sale_docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostCount {
    @Id
    @Column(name = "DOC_ID", nullable = false)
    public Long doc_id;

    @Column(name = "DOC_COUNT")
    public String doc_count;
}
