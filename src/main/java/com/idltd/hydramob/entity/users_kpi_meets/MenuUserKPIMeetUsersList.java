package com.idltd.hydramob.entity.users_kpi_meets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserKPIMeetUsersList {
    @Id
    @Column(name = "USER_KPI_MEET_USER_ID", nullable = false)
    public Long user_kpi_meet_user_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "USER_KPI_MEET_CHECK")
    public Long user_kpi_meet_check;
}
