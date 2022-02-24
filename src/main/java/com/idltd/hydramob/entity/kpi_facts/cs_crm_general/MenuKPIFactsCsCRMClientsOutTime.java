package com.idltd.hydramob.entity.kpi_facts.cs_crm_general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIFactsCsCRMClientsOutTime {
    @Id
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE", nullable = false)
    public String cnt_identifycode;

    @Column(name = "CNT_CREATEDATE", nullable = false)
    public String cnt_createdate;

    @Column(name = "ACT_DATE", nullable = false)
    public String act_date;

    @Column(name = "ACT_LAST_DATE", nullable = false)
    public String act_last_date;

    @Column(name = "ACT_COUNT", nullable = false)
    public Long act_count;

    @Column(name = "REQ_DATE", nullable = false)
    public String req_date;

    @Column(name = "REQ_LAST_DATE", nullable = false)
    public String req_last_date;

    @Column(name = "REQ_COUNT", nullable = false)
    public Long req_count;

    @Column(name = "TEND_DATE", nullable = false)
    public String tend_date;

    @Column(name = "TEND_LAST_DATE", nullable = false)
    public String tend_last_date;

    @Column(name = "TEND_COUNT", nullable = false)
    public String tend_count;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;

    @Column(name = "CNTUL_ID")
    public Long cntul_id;

    @Column(name = "BLOCK_CHECK")
    public Long block_check;
}
