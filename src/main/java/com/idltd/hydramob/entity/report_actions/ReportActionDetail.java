package com.idltd.hydramob.entity.report_actions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportActionDetail {
    @Id
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "USER_CALL", nullable = false)
    public Long user_call;

    @Column(name = "USER_CALL_SUCC", nullable = false)
    public Long user_call_succ;

    @Column(name = "USER_MEET", nullable = false)
    public Long user_meet;

    @Column(name = "USER_MEET_SUCC", nullable = false)
    public Long user_meet_succ;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "EMPTY_COLUMN", nullable = false)
    public String empty_column;
}
