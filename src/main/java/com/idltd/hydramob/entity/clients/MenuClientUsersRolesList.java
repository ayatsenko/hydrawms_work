package com.idltd.hydramob.entity.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientUsersRolesList {
    @Id
    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "CS_CNTUL_ID")
    public Long cs_cntul_id;

    @Column(name = "CS_USER_ID")
    public Long cs_user_id;

    @Column(name = "CS_USER_NAME")
    public String cs_user_name;

    @Column(name = "CS_START_DATE")
    public String cs_start_date;

    @Column(name = "CS_END_DATE")
    public String cs_end_date;

    @Column(name = "CS_MAIN_ID")
    public Long cs_main_id;

    @Column(name = "CS_PERSENT")
    public String cs_persent;

    @Column(name = "OPS_CNTUL_ID")
    public Long ops_cntul_id;

    @Column(name = "OPS_USER_ID")
    public Long ops_user_id;

    @Column(name = "OPS_USER_NAME")
    public String ops_user_name;

    @Column(name = "OPS_START_DATE")
    public String ops_start_date;

    @Column(name = "OPS_END_DATE")
    public String ops_end_date;

    @Column(name = "OPS_MAIN_ID")
    public Long ops_main_id;

    @Column(name = "OPS_PERSENT")
    public String ops_persent;

    @Column(name = "CNTSERL_DATE")
    public String cntserl_date;

    @Column(name = "USERS_COLOUR")
    public String users_colour;

    @Column(name = "USERS_ADD_CHECK")
    public String users_add_check;
}
