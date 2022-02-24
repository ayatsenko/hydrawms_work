package com.idltd.hydramob.entity.kpi_facts.cs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIFactsCsClientsFin {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "SER_SNAME")
    public String ser_sname;

    @Column(name = "FIN_SUM")
    public String fin_sum;

    @Column(name = "BLOCK_CHECK")
    public Long block_check;
}
