package com.idltd.hydramob.entity.wms_pairs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuWMSPairsUsers {
    @Id
    @Column(name = "PAIR_USER_LINK_ID", nullable = false)
    public Long pair_user_link_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;


    @Column(name = "ADD_DATE")
    public String add_date;

    @Column(name = "ADD_TIME")
    public String add_time;

    @Column(name = "ADD_USER_ID")
    public Long add_user_id;

    @Column(name = "ADD_USER_NAME")
    public String add_user_name;

    @Column(name = "ADD_ROLE_ID")
    public Long add_role_id;


    @Column(name = "CLOSE_DATE")
    public String close_date;

    @Column(name = "CLOSE_TIME")
    public String close_time;

    @Column(name = "CLOSE_USER_ID")
    public Long close_user_id;

    @Column(name = "CLOSE_USER_NAME")
    public String close_user_name;

    @Column(name = "CLOSE_ROLE_ID")
    public Long close_role_id;


    @Column(name = "STATUS_LINK_ID")
    public Long status_link_id;

    @Column(name = "STATUS_LINK_NAME")
    public String status_link_name;

    @Column(name = "STATUS_LINK_COLOR")
    public String status_link_color;

    @Column(name = "PAIR_AUTO_ID")
    public Long pair_auto_id;
}