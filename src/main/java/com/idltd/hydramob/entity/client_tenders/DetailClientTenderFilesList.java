package com.idltd.hydramob.entity.client_tenders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailClientTenderFilesList {
    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "REQ_NAME", nullable = false)
    public String req_name;

    @Id
    @Column(name = "REQ_STORE_ID", nullable = false)
    public Long req_store_id;

    @Column(name = "REQ_STORE_DATE")
    public String req_store_date;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "REQ_STORE_FILENAME")
    public String req_store_filename;
}
