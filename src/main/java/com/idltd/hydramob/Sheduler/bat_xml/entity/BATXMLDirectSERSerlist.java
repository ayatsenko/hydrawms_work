package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SERLIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDirectSERSerlist {
    @XmlElement(name = "RECTYP")
    @Column(name = "RECTYP")
    private String rectyp;

    @XmlElement(name = "PROD_NR")
    @Column(name = "PROD_NR")
    private String prod_nr;

    @XmlElement(name = "SERIAL_NR")
    @Column(name = "SERIAL_NR")
    private String serial_nr;

    @XmlElement(name = "DATE_PROD")
    @Column(name = "DATE_PROD")
    private String date_prod;

    @XmlElement(name = "DATE_EXPIRE")
    @Column(name = "DATE_EXPIRE")
    private String date_expire;

    @XmlElement(name = "ONE_C_ID")
    @Column(name = "ONE_C_ID")
    private String one_c_id;

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getProd_nr() {
        return prod_nr;
    }

    public void setProd_nr(String prod_nr) {
        this.prod_nr = prod_nr;
    }

    public String getSerial_nr() {
        return serial_nr;
    }

    public void setSerial_nr(String serial_nr) {
        this.serial_nr = serial_nr;
    }

    public String getDate_prod() {
        return date_prod;
    }

    public void setDate_prod(String date_prod) {
        this.date_prod = date_prod;
    }

    public String getDate_expire() {
        return date_expire;
    }

    public void setDate_expire(String date_expire) {
        this.date_expire = date_expire;
    }

    public String getOne_c_id() {
        return one_c_id;
    }

    public void setOne_c_id(String one_c_id) {
        this.one_c_id = one_c_id;
    }
}
