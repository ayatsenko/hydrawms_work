package com.idltd.hydramob.entity.kpi_facts.ops;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuKPIFactsOpsClientsFinCount {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "FIN_SUM")
    public String fin_sum;

    @Column(name = "FIN_COUNT")
    public String fin_count;

    @Column(name = "BLOCK_CHECK")
    public Long block_check;
}
