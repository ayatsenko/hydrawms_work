package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailClientDocFilesList {
    @Id
    @Column(name = "CNT_DOC_FILE_ID", nullable = false)
    public Long cnt_doc_file_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNT_DOC_ID")
    public Long cnt_doc_id;

    @Column(name = "CNT_DOC_NUM")
    public String cnt_doc_num;

    @Column(name = "CNT_DOC_FILE_NAME")
    public String cnt_doc_file_name;

    @Column(name = "CNT_DOC_FILE_DATE")
    public String cnt_doc_file_date;
}
