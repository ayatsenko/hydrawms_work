package com.idltd.hydramob.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserServiceList {
    @Id
    public Long usrserl_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "USRSERL_ADD_DATE", nullable = false)
    public String usrserl_add_date;
}
