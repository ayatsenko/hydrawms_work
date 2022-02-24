package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTransportDriversList {
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
}
