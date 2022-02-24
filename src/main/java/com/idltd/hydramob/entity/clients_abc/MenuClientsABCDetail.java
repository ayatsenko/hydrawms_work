package com.idltd.hydramob.entity.clients_abc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClientsABCDetail {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "FIN_SUM")
    public String fin_sum;

    @Column(name = "FIN_PERS")
    public String fin_pers;

    @Column(name = "FIN_VALUE")
    public String fin_value;

    @Column(name = "FIN_COLOR")
    public String fin_color;

    @Column(name = "FIN_ALL_SUM")
    public String fin_all_sum;

    @Column(name = "CNT_CLASS_ID")
    public Long cnt_class_id;

    @Column(name = "CNT_CLASS_SNAME")
    public String cnt_class_sname;

    @Column(name = "CNT_CLASS_COLOR")
    public String cnt_class_color;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
