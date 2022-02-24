package com.idltd.hydramob.entity.users_kpi_meets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserKPIMeetsUsersList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;
}
