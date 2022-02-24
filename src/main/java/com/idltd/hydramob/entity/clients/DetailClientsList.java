package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailClientsList {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CNT_SNAME")
    public String cnt_sname;

    @Column(name = "CNT_NAME_ENG")
    public String cnt_name_eng;

    @Column(name = "CNT_IDENTIFYCODE")
    public String cnt_identifycode;

    @Column(name = "OWT_ID")
    public Long owt_id;

    @Column(name = "CS_ID")
    public Long cs_id;

    @Column(name = "CNT_DESCRIPTION")
    public String cnt_description;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "CNT_NOTE")
    public String cnt_note;

    @Column(name = "CNT_RESIDENT")
    public Long cnt_resident;

    @Column(name = "CNT_SOURCE_ID")
    public Long cnt_source_id;

    @Column(name = "COUNTRY_ID")
    public Long country_id;

    @Column(name = "CNT_LAW_TYPE_ID")
    public Long cnt_law_type_id;
}
