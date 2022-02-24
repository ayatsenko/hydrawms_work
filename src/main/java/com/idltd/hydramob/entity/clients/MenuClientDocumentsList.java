package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientDocumentsList {
    @Id
    @Column(name = "CNT_DOC_ID", nullable = false)
    public Long cnt_doc_id;

    @Column(name = "CNT_DOC_START_DATE")
    public String cnt_doc_start_date;

    @Column(name = "CNT_DOC_END_DATE")
    public String cnt_doc_end_date;

    @Column(name = "CNT_DOC_NUM")
    public String cnt_doc_num;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
