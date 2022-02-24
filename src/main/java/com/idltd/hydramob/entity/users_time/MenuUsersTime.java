package com.idltd.hydramob.entity.users_time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUsersTime {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "LOG_COUNT")
    public String log_count;

    @Column(name = "LAST_IN_DATE")
    public String last_in_date;
}
