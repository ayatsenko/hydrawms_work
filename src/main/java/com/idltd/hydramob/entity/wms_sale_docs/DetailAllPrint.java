package com.idltd.hydramob.entity.wms_sale_docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailAllPrint {
    @Id
    @Column(name = "DOC_ID", nullable = false)
    public Long doc_id;

    @Column(name = "ALL_POST_ID")
    public String all_post_id;
}
