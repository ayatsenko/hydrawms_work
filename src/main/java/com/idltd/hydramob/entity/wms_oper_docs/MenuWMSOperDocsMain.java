package com.idltd.hydramob.entity.wms_oper_docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSOperDocsMain {
    @Id
    @Column(name = "DOC_ID", nullable = false)
    public Long doc_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "API_KEY", nullable = false)
    public String api_key;

    @Column(name = "POST_ID", nullable = false)
    public String post_id;

    @Column(name = "POST_NAME", nullable = false)
    public String post_name;

    @Column(name = "DOC_DATE", nullable = false)
    public String doc_date;

    @Column(name = "ADD_TIME", nullable = false)
    public String add_time;

    @Column(name = "ADD_USER_ID", nullable = false)
    public Long add_user_id;

    @Column(name = "ADD_USER_NAME", nullable = false)
    public String add_user_name;

    @Column(name = "ADD_ROLE_ID")
    public Long add_role_id;

    @Column(name = "CLOSE_TIME")
    public String close_time;

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

    @Column(name = "DOC_AVG_TIME")
    public String doc_avg_time;

    @Column(name = "DOC_POST_AVG_TIME")
    public String doc_post_avg_time;

    @Column(name = "DOC_POST_COUNT")
    public String doc_post_count;

    @Column(name = "DOC_CLIENT_ID")
    public String doc_client_id;
}