package com.idltd.hydramob.entity.finance_abc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFinanceABCDetail {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "FIN_SUM")
    public String fin_sum;

    @Column(name = "FIN_PERS")
    public String fin_pers;

    @Column(name = "FIN_VALUE")
    public String fin_value;

    @Column(name = "FIN_COLOR")
    public String fin_color;


    @Column(name = "REQ_SUM")
    public String req_sum;

    @Column(name = "REQ_PERS")
    public String req_pers;

    @Column(name = "REQ_VALUE")
    public String req_value;

    @Column(name = "REQ_COLOR")
    public String req_color;


    @Column(name = "TEND_SUM")
    public String tend_sum;

    @Column(name = "TEND_PERS")
    public String tend_pers;

    @Column(name = "TEND_VALUE")
    public String tend_value;

    @Column(name = "TEND_COLOR")
    public String tend_color;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
