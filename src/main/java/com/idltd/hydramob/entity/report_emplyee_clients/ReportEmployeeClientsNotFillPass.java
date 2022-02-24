package com.idltd.hydramob.entity.report_emplyee_clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportEmployeeClientsNotFillPass {
    @Id
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE", nullable = false)
    public String cnt_identifycode;

    @Column(name = "CNT_CREATEDATE", nullable = false)
    public String cnt_createdate;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;

    @Column(name = "CNTUL_ID")
    public Long cntul_id;
}
