package com.idltd.hydramob.entity.sheduler;

import javax.persistence.*;

@Entity
@Table(name = "BANKS_TEMP")
public class ShedulerBankDetail {
    @Column(name = "SE_ID", nullable = false)
    public Long se_id;

    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

    @Column(name = "IS_LOADED")
    public Long is_loaded;

    @Column(name = "MAIN_MFO")
    public Long main_mfo;

    @Column(name = "MAIN_NAME")
    public String main_name;

    @Column(name = "COMPANY_NAME")
    public String company_name;

    @Column(name = "BANK_EDRPOU")
    public Long bank_edrpou;

    @Column(name = "BANK_SNAME")
    public String bank_sname;

    @Column(name = "BANK_NKB")
    public String bank_nkb;

    @Column(name = "BANK_TYPE")
    public Long bank_type;

    @Column(name = "BANK_KU")
    public Long bank_ku;

    @Column(name = "BANK_OBL_NAME")
    public String bank_obl_name;

    @Column(name = "BANK_DEPCODE")
    public String bank_depcode;

    @Column(name = "BANK_ZIPCODE")
    public String bank_zipcode;

    @Column(name = "BANK_CITY_TYPE")
    public String bank_city_type;

    @Column(name = "BANK_CITY_NAME")
    public String bank_city_name;

    @Column(name = "BANK_ADRESS")
    public String bank_adress;

    @Column(name = "BANK_PHONE_CODE")
    public String bank_phone_code;

    @Column(name = "BANK_PHONE")
    public String bank_phone;

    @Column(name = "BANK_KSTAN")
    public Long bank_kstan;

    @Column(name = "BANK_NSTAN")
    public String bank_nstan;

    @Column(name = "BANK_DSTAN")
    public String bank_dstan;

    @Column(name = "BANK_OPEN_DATE")
    public String bank_open_date;

    @Column(name = "BANK_CLOSE_DATE")
    public String bank_close_date;

    @Column(name = "MAIN_STAN")
    public String main_stan;

    @Column(name = "MAIN_NSTAN")
    public String main_nstan;

    @Column(name = "BANK_MFO")
    public Long bank_mfo;
}
