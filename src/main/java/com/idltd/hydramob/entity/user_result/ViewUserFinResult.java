package com.idltd.hydramob.entity.user_result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_FIN_RESULT")
public class ViewUserFinResult {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;

    @Column(name = "FIN_1", nullable = false)
    public Float fin_1;

    @Column(name = "FIN_1_COLOR", nullable = false)
    public String fin_1_color;

    @Column(name = "FIN_2", nullable = false)
    public Float fin_2;

    @Column(name = "FIN_2_COLOR", nullable = false)
    public String fin_2_color;

    @Column(name = "FIN_3", nullable = false)
    public Float fin_3;

    @Column(name = "FIN_3_COLOR", nullable = false)
    public String fin_3_color;

    @Column(name = "FIN_4", nullable = false)
    public Float fin_4;

    @Column(name = "FIN_4_COLOR", nullable = false)
    public String fin_4_color;

    @Column(name = "FIN_5", nullable = false)
    public Float fin_5;

    @Column(name = "FIN_5_COLOR", nullable = false)
    public String fin_5_color;

    @Column(name = "FIN_6", nullable = false)
    public Float fin_6;

    @Column(name = "FIN_6_COLOR", nullable = false)
    public String fin_6_color;

    @Column(name = "FIN_7", nullable = false)
    public Float fin_7;

    @Column(name = "FIN_7_COLOR", nullable = false)
    public String fin_7_color;

    @Column(name = "FIN_8", nullable = false)
    public Float fin_8;

    @Column(name = "FIN_8_COLOR", nullable = false)
    public String fin_8_color;

    @Column(name = "FIN_9", nullable = false)
    public Float fin_9;

    @Column(name = "FIN_9_COLOR", nullable = false)
    public String fin_9_color;

    @Column(name = "FIN_10", nullable = false)
    public Float fin_10;

    @Column(name = "FIN_10_COLOR", nullable = false)
    public String fin_10_color;

    @Column(name = "FIN_11", nullable = false)
    public Float fin_11;

    @Column(name = "FIN_11_COLOR", nullable = false)
    public String fin_11_color;

    @Column(name = "FIN_12", nullable = false)
    public Float fin_12;

    @Column(name = "FIN_12_COLOR", nullable = false)
    public String fin_12_color;

    @Column(name = "FIN_ALL", nullable = false)
    public Float fin_all;

    @Column(name = "FIN_ALL_COLOR", nullable = false)
    public String fin_all_color;
}
