package com.idltd.hydramob.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "REQUEST_STORE")
public class TenderFile {
    @SequenceGenerator(name = "REQUEST_STORE_SEQ", sequenceName = "REQUEST_STORE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_STORE_SEQ")

    @Id
    public Long req_store_id;

    @Column(name = "REQ_ID", nullable = false)
    public Long req_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "REQ_STORE_DATE", nullable = false)
    public Date req_store_date;

    @Column(name = "REQ_STORE_FILENAME", nullable = false)
    public String req_store_filename;

    @Lob
    @Column(name = "REQ_STORE_FILE_BODY", nullable = false)
    public Blob req_store_file_body;

    @Column(name = "REQ_STORE_FILE_CONTENTTYPE", nullable = false)
    public String req_store_file_contenttype;
}
