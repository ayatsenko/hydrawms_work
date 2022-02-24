package com.idltd.hydramob.entity.tms_cars;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuTMSCars {
    @Id
    @Column(name = "TMS_CAR_ID", nullable = false)
    public Long tms_car_id;

    @Column(name = "TMS_CAR_NAME", nullable = false)
    public String tms_car_name;

    @Column(name = "TMS_CAR_NUMBER")
    public String tms_car_number;

    @Column(name = "TMS_CAR_PHONE")
    public String tms_car_phone;

    @Column(name = "TMS_CAR_TELEGRAM")
    public Long tms_car_telegram;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
