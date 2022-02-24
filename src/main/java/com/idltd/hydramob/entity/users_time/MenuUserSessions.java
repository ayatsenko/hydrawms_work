package com.idltd.hydramob.entity.users_time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserSessions {
    @Id
    @Column(name = "USR_SYS_LOG_ID", nullable = false)
    public Long usr_sys_log_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "USR_SYS_IN_DATE")
    public String usr_sys_in_date;

    @Column(name = "USR_SYS_OUT_DATE")
    public String usr_sys_out_date;
}
