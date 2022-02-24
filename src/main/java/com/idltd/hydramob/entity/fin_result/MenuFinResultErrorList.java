package com.idltd.hydramob.entity.fin_result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFinResultErrorList {
    @Id
    @Column(name = "SFAD_ID", nullable = false)
    public Long sfad_id;

    @Column(name = "FA_DOC", nullable = false)
    public String fa_doc;

    @Column(name = "FA_DATE")
    public String fa_date;

    @Column(name = "FA_WAY")
    public String fa_way;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE")
    public String cnt_identifycode;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "FA_DIVISION")
    public String fa_division;

    @Column(name = "SUMM")
    public String summ;

    @Column(name = "ID_DELETE")
    public Long id_delete;

    @Column(name = "FADS_ID")
    public Long fads_id;

    @Column(name = "FADS_SNAME")
    public String fads_sname;

    @Column(name = "FADS_COLOUR")
    public String fads_colour;
}
