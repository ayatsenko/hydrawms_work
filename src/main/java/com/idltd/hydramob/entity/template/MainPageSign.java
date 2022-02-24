package com.idltd.hydramob.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MainPageSign {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "USER_ID", nullable = false)
    public String user_id;


    @Column(name = "ACT_COUNT", nullable = false)
    public String act_count;

    @Column(name = "ACT_SEC_COUNT", nullable = false)
    public String act_sec_count;

    @Column(name = "ACT_SIGN", nullable = false)
    public Integer act_sign;


    @Column(name = "REQ_COUNT", nullable = false)
    public String req_count;

    @Column(name = "REQ_SEC_COUNT", nullable = false)
    public String req_sec_count;

    @Column(name = "REQ_SIGN", nullable = false)
    public Integer req_sign;


    @Column(name = "TEND_COUNT", nullable = false)
    public String tend_count;

    @Column(name = "TEND_SEC_COUNT", nullable = false)
    public String tend_sec_count;

    @Column(name = "TEND_SIGN", nullable = false)
    public Integer tend_sign;


    @Column(name = "PROD_COUNT", nullable = false)
    public String prod_count;

    @Column(name = "PROD_SEC_COUNT", nullable = false)
    public String prod_sec_count;

    @Column(name = "PROD_SIGN", nullable = false)
    public Integer prod_sign;


    @Column(name = "PROFIT_COUNT", nullable = false)
    public String profit_count;

    @Column(name = "PROFIT_SEC_COUNT", nullable = false)
    public String profit_sec_count;

    @Column(name = "PROFIT_SIGN", nullable = false)
    public Integer profit_sign;


    @Column(name = "TMS_COUNT", nullable = false)
    public String tms_count;

    @Column(name = "TMS_SEC_COUNT", nullable = false)
    public String tms_sec_count;

    @Column(name = "TMS_SIGN", nullable = false)
    public Integer tms_sign;
}