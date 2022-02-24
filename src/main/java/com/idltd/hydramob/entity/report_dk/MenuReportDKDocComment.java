package com.idltd.hydramob.entity.report_dk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuReportDKDocComment {
    @Column(name = "DKM_ID")
    public Long dkm_id;

    @Column(name = "DKMD_ID")
    public Long dkmd_id;

    @Column(name = "DKMDD_ID")
    public Long dkmdd_id;

    @Id
    @Column(name = "DK_COM_ID")
    public Long dk_com_id;

    @Column(name = "DK_COM_TEXT")
    public String dk_com_text;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "SER_ID")
    public Long ser_id;
}
