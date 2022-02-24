package com.idltd.hydramob.entity.transports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTransportsList {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

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

    @Column(name = "TMS_CONTT_ID")
    public Long tms_contt_id;

    @Column(name = "TMS_CONTT_NUM")
    public String tms_contt_num;

    @Column(name = "TMS_CONTT_DATE")
    public String tms_contt_date;

    @Column(name = "TMS_LIC_NUM")
    public String tms_lic_num;

    @Column(name = "TMS_LIC_DATE")
    public String tms_lic_date;

    @Column(name = "TMS_INS_NUM")
    public String tms_ins_num;

    @Column(name = "TMS_INS_DATE")
    public String tms_ins_date;

    @Column(name = "TMS_CONTT_CHECK")
    public Long tms_contt_check;

    @Column(name = "TMS_CONTT_COUNT")
    public Long tms_contt_count;

    @Column(name = "TMS_CONTT_NEXT_DATE")
    public String tms_contt_next_date;

    @Column(name = "TMS_TIR_CHECK")
    public Long tms_tir_check;

    @Column(name = "TMS_ADR_CHECK")
    public Long tms_adr_check;

    @Column(name = "TMS_EKMT_CHECK")
    public Long tms_ekmt_check;

    @Column(name = "TRANS_ID")
    public Long trans_id;

    @Column(name = "SEA_AGENT_ID")
    public Long sea_agent_id;

    @Column(name = "TRECKING_URL")
    public String trecking_url;

    @Column(name = "AIR_ID")
    public Long air_id;

    @Column(name = "SHIP_ID")
    public Long ship_id;

    @Column(name = "CONSEGNEE_ID")
    public Long consegnee_id;

    @Column(name = "SHIPPER_ID")
    public Long shipper_id;
}
