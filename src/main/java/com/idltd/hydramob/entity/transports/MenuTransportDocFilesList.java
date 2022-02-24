package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTransportDocFilesList {
    @Id
    @Column(name = "CNT_DOC_FILE_ID", nullable = false)
    public Long cnt_doc_file_id;

    @Column(name = "CNT_DOC_FILE_DATE")
    public String cnt_doc_file_date;

    @Column(name = "CNT_DOC_FILE_NAME")
    public String cnt_doc_file_name;
}
