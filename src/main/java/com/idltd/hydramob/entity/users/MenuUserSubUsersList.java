package com.idltd.hydramob.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserSubUsersList {
    @Id
    public Long usrusrl_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "SUB_USER_ID", nullable = false)
    public Long sub_user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "USRUSRL_ADD_DATE", nullable = false)
    public String usrusrl_add_date;
}
