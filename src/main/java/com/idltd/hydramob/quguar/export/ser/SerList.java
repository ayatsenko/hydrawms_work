package com.idltd.hydramob.quguar.export.ser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SERLIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class SerList {
    @XmlElement(required =true)
    private String RECTYP = "";
    @XmlElement(required =true)
    private String PRODUCT_NR = "";
    @XmlElement(required =true)
    private String SERIAL_NR = "";
    @XmlElement(required =true)
    private String DATE_PROD = "";
    @XmlElement(required =true)
    private String DATE_EXPIRE = "";
    @XmlElement(required =true)
    private String ONE_C_ID = "";

    public String getRECTYP() {
        return RECTYP;
    }

    public void setRECTYP(String RECTYP) {
        this.RECTYP = RECTYP;
    }

    public String getPRODUCT_NR() {
        return PRODUCT_NR;
    }

    public void setPRODUCT_NR(String PRODUCT_NR) {
        this.PRODUCT_NR = PRODUCT_NR;
    }

    public String getSERIAL_NR() {
        return SERIAL_NR;
    }

    public void setSERIAL_NR(String SERIAL_NR) {
        this.SERIAL_NR = SERIAL_NR;
    }

    public String getDATE_PROD() {
        return DATE_PROD;
    }

    public void setDATE_PROD(String DATE_PROD) {
        this.DATE_PROD = DATE_PROD;
    }

    public String getDATE_EXPIRE() {
        return DATE_EXPIRE;
    }

    public void setDATE_EXPIRE(String DATE_EXPIRE) {
        this.DATE_EXPIRE = DATE_EXPIRE;
    }

    public String getONE_C_ID() {
        return ONE_C_ID;
    }

    public void setONE_C_ID(String ONE_C_ID) {
        this.ONE_C_ID = ONE_C_ID;
    }
}
