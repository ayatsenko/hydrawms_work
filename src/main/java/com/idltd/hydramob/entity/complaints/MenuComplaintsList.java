package com.idltd.hydramob.entity.complaints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuComplaintsList {
    @Id
    @Column(name = "CNT_COMP_ID", nullable = false)
    public Long cnt_comp_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "DEP_ID")
    public Long dep_id;

    @Column(name = "DEP_NAME")
    public String dep_name;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "CNT_COMP_DATE")
    public String cnt_comp_date;

    @Column(name = "CNT_COMP_TEXT")
    public String cnt_comp_text;
}
