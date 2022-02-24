package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLDClaimTransList {
    @Id
    @Column(name = "CLMTRL_ID", nullable = false)
    public Long clmtrl_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CNTC_TYPE_SNAME")
    public String cntc_type_sname;

    @Column(name = "CNTC_TYPE_BRAND")
    public String cntc_type_brand;

    @Column(name = "CNTC_TYPE_NUMBER")
    public String cntc_type_number;

    @Column(name = "CNTT_TYPE_SNAME")
    public String cntt_type_sname;

    @Column(name = "CNTT_TYPE_BRAND")
    public String cntt_type_brand;

    @Column(name = "CNTT_TYPE_NUMBER")
    public String cntt_type_number;

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

    @Column(name = "CNTD_EMAIL")
    public String cntd_email;
}
