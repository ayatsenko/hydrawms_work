package com.idltd.hydramob.entity.client_lost;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRAGENT_LOST")
public class ViewClientLostUser {
    @Id
    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "FIN_1", nullable = false)
    public Float fin_1;

    @Column(name = "CHECK_1", nullable = false)
    public Long check_1;

    @Column(name = "FIN_2", nullable = false)
    public Float fin_2;

    @Column(name = "CHECK_2", nullable = false)
    public Long check_2;

    @Column(name = "FIN_3", nullable = false)
    public Float fin_3;

    @Column(name = "CHECK_3", nullable = false)
    public Long check_3;

    @Column(name = "FIN_4", nullable = false)
    public Float fin_4;

    @Column(name = "CHECK_4", nullable = false)
    public Long check_4;

    @Column(name = "FIN_5", nullable = false)
    public Float fin_5;

    @Column(name = "CHECK_5", nullable = false)
    public Long check_5;

    @Column(name = "FIN_6", nullable = false)
    public Float fin_6;

    @Column(name = "CHECK_6", nullable = false)
    public Long check_6;

    @Column(name = "FIN_7", nullable = false)
    public Float fin_7;

    @Column(name = "CHECK_7", nullable = false)
    public Long check_7;

    @Column(name = "FIN_8", nullable = false)
    public Float fin_8;

    @Column(name = "CHECK_8", nullable = false)
    public Long check_8;

    @Column(name = "FIN_9", nullable = false)
    public Float fin_9;

    @Column(name = "CHECK_9", nullable = false)
    public Long check_9;

    @Column(name = "FIN_10", nullable = false)
    public Float fin_10;

    @Column(name = "CHECK_10", nullable = false)
    public Long check_10;

    @Column(name = "FIN_11", nullable = false)
    public Float fin_11;

    @Column(name = "CHECK_11", nullable = false)
    public Long check_11;

    @Column(name = "FIN_12", nullable = false)
    public Float fin_12;

    @Column(name = "CHECK_12", nullable = false)
    public Long check_12;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;
}
