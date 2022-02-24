package com.idltd.hydramob.entity.clients;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "CONTRAGENT_DOC_FILE")
public class ClientDocFile {
    @Id
    @SequenceGenerator(name = "CONTRAGENT_DOC_FILE_SEQ", sequenceName = "CONTRAGENT_DOC_FILE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTRAGENT_DOC_FILE_SEQ")
    public Long cnt_doc_file_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_DOC_ID", nullable = false)
    public Long cnt_doc_id;

    @Column(name = "CNT_DOC_FILE_DATE", nullable = false)
    public Date cnt_doc_file_date;

    @Column(name = "CNT_DOC_FILE_NAME", nullable = false)
    public String cnt_doc_file_name;

    @Lob
    @Column(name = "cnt_doc_file_body", nullable = false)
    public Blob cnt_doc_file_body;

    @Column(name = "CNT_DOC_FILE_CONTENTTYPE", nullable = false)
    public String cnt_doc_file_contenttype;

}
