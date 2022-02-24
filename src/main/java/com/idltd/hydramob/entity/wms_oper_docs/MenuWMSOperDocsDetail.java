package com.idltd.hydramob.entity.wms_oper_docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSOperDocsDetail {
    @Id
    @Column(name = "DOC_LINK_ID", nullable = false)
    public Long doc_link_id;

    @Column(name = "DOC_ID", nullable = false)
    public Long doc_id;

    @Column(name = "ROW_ID")
    public Long row_id;

    @Column(name = "ADD_DATE")
    public String add_date;

    @Column(name = "CLIENT_ID")
    public Long client_id;

    @Column(name = "CLIENT_POST_ID")
    public String client_post_id;

    @Column(name = "CLIENT_POST_REF")
    public String client_post_ref;
}