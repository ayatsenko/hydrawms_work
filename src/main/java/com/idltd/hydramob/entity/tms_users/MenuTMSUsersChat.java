package com.idltd.hydramob.entity.tms_users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTMSUsersChat {
    @Id
    @Column(name = "TMS_CHAT_ID", nullable = false)
    public Long tms_chat_id;

    @Column(name = "TMS_CHAT_CREATE", nullable = false)
    public String tms_chat_create;

    @Column(name = "TMS_CAR_ID", nullable = false)
    public Long tms_car_id;

    @Column(name = "TMS_CAR_NAME", nullable = false)
    public String tms_car_name;

    @Column(name = "TMS_CHAT_TEXT", nullable = false)
    public String tms_chat_text;
}
