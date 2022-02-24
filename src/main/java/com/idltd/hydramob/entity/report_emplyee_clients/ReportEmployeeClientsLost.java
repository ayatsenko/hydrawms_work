package com.idltd.hydramob.entity.report_emplyee_clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportEmployeeClientsLost {
    @Id
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE", nullable = false)
    public String cnt_identifycode;

    @Column(name = "CNT_CREATEDATE", nullable = false)
    public String cnt_createdate;

    @Column(name = "FIN_DATE", nullable = false)
    public String fin_date;

    @Column(name = "FIN_LAST_DATE", nullable = false)
    public String fin_last_date;

    @Column(name = "FIN_COUNT", nullable = false)
    public Long fin_count;

    @Column(name = "FIN_SUM", nullable = false)
    public String fin_sum;

    @Column(name = "FIN_YEAR_SUM", nullable = false)
    public String fin_year_sum;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;

    @Column(name = "CNTUL_ID")
    public Long cntul_id;
}
