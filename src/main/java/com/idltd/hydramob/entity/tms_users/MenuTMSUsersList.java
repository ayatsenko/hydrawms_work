package com.idltd.hydramob.entity.tms_users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTMSUsersList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;
}
