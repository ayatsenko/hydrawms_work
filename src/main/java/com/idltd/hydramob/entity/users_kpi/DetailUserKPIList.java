package com.idltd.hydramob.entity.users_kpi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailUserKPIList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "MEETS_PLAN")
    public String meets_plan;

    @Column(name = "CALLS_PLAN")
    public String calls_plan;

    @Column(name = "REQ_PLAN")
    public String req_plan;

    @Column(name = "FIN_PLAN")
    public String fin_plan;
}
