package com.idltd.hydramob.entity.report_emplyee_clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportEmployeeClientsNotFill {
    @Id
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE")
    public String cnt_identifycode;

    @Column(name = "CNT_CREATEDATE")
    public String cnt_createdate;

    @Column(name = "FIN_DATE")
    public String fin_date;

    @Column(name = "FIN_LAST_DATE")
    public String fin_last_date;

    @Column(name = "FIN_COUNT")
    public Long fin_count;

    @Column(name = "FIN_SUM")
    public String fin_sum;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;

    @Column(name = "CNTUL_ID")
    public Long cntul_id;
}
