package com.idltd.hydramob.entity.users_kpi_facts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserKPIFactsMainList {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "MONTH_1_SUM")
    public String month_1_sum;

    @Column(name = "MONTH_1_BONUS")
    public String month_1_bonus;

    @Column(name = "MONTH_2_SUM")
    public String month_2_sum;

    @Column(name = "MONTH_2_BONUS")
    public String month_2_bonus;

    @Column(name = "MONTH_3_SUM")
    public String month_3_sum;

    @Column(name = "MONTH_3_BONUS")
    public String month_3_bonus;

    @Column(name = "MONTH_4_SUM")
    public String month_4_sum;

    @Column(name = "MONTH_4_BONUS")
    public String month_4_bonus;

    @Column(name = "MONTH_5_SUM")
    public String month_5_sum;

    @Column(name = "MONTH_5_BONUS")
    public String month_5_bonus;

    @Column(name = "MONTH_6_SUM")
    public String month_6_sum;

    @Column(name = "MONTH_6_BONUS")
    public String month_6_bonus;

    @Column(name = "MONTH_7_SUM")
    public String month_7_sum;

    @Column(name = "MONTH_7_BONUS")
    public String month_7_bonus;

    @Column(name = "MONTH_8_SUM")
    public String month_8_sum;

    @Column(name = "MONTH_8_BONUS")
    public String month_8_bonus;

    @Column(name = "MONTH_9_SUM")
    public String month_9_sum;

    @Column(name = "MONTH_9_BONUS")
    public String month_9_bonus;

    @Column(name = "MONTH_10_SUM")
    public String month_10_sum;

    @Column(name = "MONTH_10_BONUS")
    public String month_10_bonus;

    @Column(name = "MONTH_11_SUM")
    public String month_11_sum;

    @Column(name = "MONTH_11_BONUS")
    public String month_11_bonus;

    @Column(name = "MONTH_12_SUM")
    public String month_12_sum;

    @Column(name = "MONTH_12_BONUS")
    public String month_12_bonus;

    @Column(name = "MONTH_ALL_SUM")
    public String month_all_sum;

    @Column(name = "MONTH_ALL_BONUS")
    public String month_all_bonus;
}
