package com.idltd.hydramob.entity.wms_sale_docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSSaleDocsMain {
    @Id
    @Column(name = "DOC_ID", nullable = false)
    public Long doc_id;

    @Column(name = "POST_ID", nullable = false)
    public String post_id;

    @Column(name = "POST_NAME", nullable = false)
    public String post_name;

    @Column(name = "ADD_DATE", nullable = false)
    public String add_date;

    @Column(name = "ADD_USER_ID", nullable = false)
    public Long add_user_id;

    @Column(name = "ADD_USER_NAME", nullable = false)
    public String add_user_name;

    @Column(name = "ADD_ROLE_ID")
    public Long add_role_id;

    @Column(name = "CLOSE_DATE")
    public String close_date;

    @Column(name = "CLOSE_USER_ID")
    public Long close_user_id;

    @Column(name = "CLOSE_USER_NAME")
    public String close_user_name;

    @Column(name = "CLOSE_ROLE_ID")
    public Long close_role_id;

    @Column(name = "STATUS_ID")
    public Long status_id;

    @Column(name = "STATUS_NAME")
    public String status_name;

    @Column(name = "STATUS_COLOR")
    public String status_color;
}