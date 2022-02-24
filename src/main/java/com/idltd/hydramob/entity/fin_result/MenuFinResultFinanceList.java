package com.idltd.hydramob.entity.fin_result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFinResultFinanceList {
    @Id
    @Column(name = "FIN_ID", nullable = false)
    public Long fin_id;

    @Column(name = "FIN_NAME")
    public String fin_name;

    @Column(name = "FIN_DATE")
    public String fin_date;

    @Column(name = "FIN_WAY_SNAME")
    public String fin_way_sname;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_SURNAME")
    public String user_surname;

    @Column(name = "SER_NAME")
    public String ser_name;

    @Column(name = "FIN_SUM")
    public Float fin_sum;
}
