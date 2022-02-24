package com.idltd.hydramob.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserContragentsList {
    @Id
    public Long cntul_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNTUL_MAIN_ID", nullable = false)
    public Long cntul_main_id;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_SNAME", nullable = false)
    public String ser_sname;

    @Column(name = "CNTUL_CREATE_DATE", nullable = false)
    public String cntul_create_date;

    @Column(name = "CNTUL_START_DATE", nullable = false)
    public String cntul_start_date;

    @Column(name = "CNTUL_END_DATE", nullable = false)
    public String cntul_end_date;
}
