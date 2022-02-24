package com.idltd.hydramob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TMS_Drivers_List {
    @Id
    @Column(name = "CNTD_ID", nullable = false)
    public Long cntd_id;

    @Column(name = "CNTD_NAME")
    public String cntd_name;

    @Column(name = "CNTD_NAME_ENG")
    public String cntd_name_eng;

    @Column(name = "CNTD_NAME_DOC")
    public String cntd_name_doc;

    @Column(name = "CNTD_NAME_LISENCE")
    public String cntd_name_lisence;

    @Column(name = "CNTD_MAIN_PHONE")
    public String cntd_main_phone;

    @Column(name = "CNTD_SECOND_PHONE")
    public String cntd_second_phone;

    @Column(name = "CNTD_SECOND_EMAIL")
    public String cntd_second_email;

    @Column(name = "CNTD_FULLNAME")
    public String cntd_fullname;
}