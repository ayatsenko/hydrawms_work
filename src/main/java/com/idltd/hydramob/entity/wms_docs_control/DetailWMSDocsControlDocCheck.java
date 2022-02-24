package com.idltd.hydramob.entity.wms_docs_control;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailWMSDocsControlDocCheck {
    @Id
    @Column(name = "CLIENT_POST_ID", nullable = false)
    public String client_post_id;

    @Column(name = "DOC_ID")
    public Long doc_id;

    @Column(name = "DOC_CONTROL_COUNT")
    public Long doc_control_count;

    @Column(name = "POST_ID")
    public String post_id;
}
