package com.idltd.hydramob.entity.dep_kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDepKPIFactCRMToplist {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CS_ID")
    public Long cs_id;

    @Column(name = "CS_NAME")
    public String cs_name;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "BLOCK_CHECK")
    public Long block_check;
}
