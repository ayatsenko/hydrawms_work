package com.idltd.hydramob.entity.users_kpi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserKPIList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "MEETS_PLAN")
    public String meets_plan;

    @Column(name = "MEETS_FACT")
    public String meets_fact;

    @Column(name = "MEETS_RESULT")
    public String meets_result;

    @Column(name = "MEETS_PERCENT")
    public String meets_percent;

    @Column(name = "MEETS_COLOR")
    public String meets_color;

    @Column(name = "CALLS_PLAN")
    public String calls_plan;

    @Column(name = "CALLS_FACT")
    public String calls_fact;

    @Column(name = "CALLS_RESULT")
    public String calls_result;

    @Column(name = "CALLS_PERCENT")
    public String calls_percent;

    @Column(name = "CALLS_COLOR")
    public String calls_color;

    @Column(name = "REQ_PLAN")
    public String req_plan;

    @Column(name = "REQ_FACT")
    public String req_fact;

    @Column(name = "REQ_RESULT")
    public String req_result;

    @Column(name = "REQ_PERCENT")
    public String req_percent;

    @Column(name = "REQ_COLOR")
    public String req_color;

    @Column(name = "FIN_PLAN")
    public String fin_plan;

    @Column(name = "FIN_FACT")
    public String fin_fact;

    @Column(name = "FIN_RESULT")
    public String fin_result;

    @Column(name = "FIN_PERCENT")
    public String fin_percent;

    @Column(name = "FIN_COLOR")
    public String fin_color;
}
