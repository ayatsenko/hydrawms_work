package com.idltd.hydramob.entity.report_emplyee_clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportEmployeeClientsDetail {
    @Id
    public Long rn;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "CNT_NOTFILL", nullable = false)
    public String cnt_notfill;

    @Column(name = "CNT_NOTFILL_PASS", nullable = false)
    public String cnt_notfill_pass;

    @Column(name = "CNT_LOST", nullable = false)
    public String cnt_lost;

    @Column(name = "CNT_OUTTIME", nullable = false)
    public String cnt_outtime;

    @Column(name = "CNT_DEBIT", nullable = false)
    public String cnt_debit;

    @Column(name = "CNT_CREDIT", nullable = false)
    public String cnt_credit;

    @Column(name = "EMPTY_COLUMN", nullable = false)
    public String empty_column;
}
