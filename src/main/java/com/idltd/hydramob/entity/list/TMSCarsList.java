package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CARS")
public class TMSCarsList {
    @Id
    public Long tms_car_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "TMS_CAR_CREATE", nullable = false)
    public String tms_car_create;

    @Column(name = "TMS_CAR_NAME", nullable = false)
    public String tms_car_name;

    @Column(name = "TMS_CAR_NUMBER", nullable = false)
    public String tms_car_number;

    @Column(name = "TMS_CAR_PHONE", nullable = false)
    public String tms_car_phone;

    @Column(name = "TMS_CAR_TELEGRAM", nullable = false)
    public Long tms_car_telegram;

    @Column(name = "VT_ID", nullable = false)
    public Long vt_id;
}