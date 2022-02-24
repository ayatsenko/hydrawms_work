package com.idltd.hydramob.entity.dep_kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDepKPIFactChiefUnpaid {
    @Id
    @Column(name = "FIN_ID", nullable = false)
    public Long fin_id;

    @Column(name = "FIN_NAME")
    public String fin_name;

    @Column(name = "FIN_DATE")
    public String fin_date;

    @Column(name = "FIN_WAY_NAME")
    public String fin_way_name;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "SER_NAME")
    public String ser_name;

    @Column(name = "FIN_SUM")
    public String fin_sum;

    @Column(name = "BLOCK_CHECK")
    public Long block_check;
}
