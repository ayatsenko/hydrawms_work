package com.idltd.hydramob.quguar.export.skh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CUSTOMER")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @XmlElement(required =true)
    private String RECTYP = "";
    @XmlElement(required =true)
    private String FIRM_NR = "";
    @XmlElement(required =true)
    private String NAME = "";
    @XmlElement(required =true)
    private String NAME_LONG = "";
    @XmlElement(required =true)
    private String FIRM_TYPE_NR = "";
    @XmlElement(required =true)
    private String FIRM_GR_NR = "";
    @XmlElement(required =true)
    private String INFO_1 = "";
    @XmlElement(required =true)
    private String ADR_STREET = "";
    @XmlElement(required =true)
    private String ADR_CITY = "";
    @XmlElement(required =true)
    private String ADR_ZIPCODE = "";
    @XmlElement(required =true)
    private String ADR_POSTBOX = "";
    @XmlElement(required =true)
    private String ADR_COUNTRY_CODE = "";
    @XmlElement(required =true)
    private String TELEFON_NR_1 = "";
    @XmlElement(required =true)
    private String FAX_NR_1 = "";
    @XmlElement(required =true)
    private String INFO_EMAIL = "";
    @XmlElement(required =true)
    private String CONTACT_PERS = "";
    @XmlElement(required =true)
    private String NIP = "";
    @XmlElement(required =true)
    private String REGON = "";
    @XmlElement(required =true)
    private String ABC_CLASS = "";
    @XmlElement(required =true)
    private String IS_ORDERER = "";
    @XmlElement(required =true)
    private String IS_SUPPLIER = "";
    @XmlElement(required =true)
    private String IS_CLIENT = "";
    @XmlElement(required =true)
    private String IS_CARRIER = "";
    @XmlElement(required =true)
    private String SCAN_GROUP_LABEL = "";

    public String getRECTYP() {
        return RECTYP;
    }

    public void setRECTYP(String RECTYP) {
        this.RECTYP = RECTYP;
    }

    public String getFIRM_NR() {
        return FIRM_NR;
    }

    public void setFIRM_NR(String FIRM_NR) {
        this.FIRM_NR = FIRM_NR;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME_LONG() {
        return NAME_LONG;
    }

    public void setNAME_LONG(String NAME_LONG) {
        this.NAME_LONG = NAME_LONG;
    }

    public String getFIRM_TYPE_NR() {
        return FIRM_TYPE_NR;
    }

    public void setFIRM_TYPE_NR(String FIRM_TYPE_NR) {
        this.FIRM_TYPE_NR = FIRM_TYPE_NR;
    }

    public String getFIRM_GR_NR() {
        return FIRM_GR_NR;
    }

    public void setFIRM_GR_NR(String FIRM_GR_NR) {
        this.FIRM_GR_NR = FIRM_GR_NR;
    }

    public String getINFO_1() {
        return INFO_1;
    }

    public void setINFO_1(String INFO_1) {
        this.INFO_1 = INFO_1;
    }

    public String getADR_STREET() {
        return ADR_STREET;
    }

    public void setADR_STREET(String ADR_STREET) {
        this.ADR_STREET = ADR_STREET;
    }

    public String getADR_CITY() {
        return ADR_CITY;
    }

    public void setADR_CITY(String ADR_CITY) {
        this.ADR_CITY = ADR_CITY;
    }

    public String getADR_ZIPCODE() {
        return ADR_ZIPCODE;
    }

    public void setADR_ZIPCODE(String ADR_ZIPCODE) {
        this.ADR_ZIPCODE = ADR_ZIPCODE;
    }

    public String getADR_POSTBOX() {
        return ADR_POSTBOX;
    }

    public void setADR_POSTBOX(String ADR_POSTBOX) {
        this.ADR_POSTBOX = ADR_POSTBOX;
    }

    public String getADR_COUNTRY_CODE() {
        return ADR_COUNTRY_CODE;
    }

    public void setADR_COUNTRY_CODE(String ADR_COUNTRY_CODE) {
        this.ADR_COUNTRY_CODE = ADR_COUNTRY_CODE;
    }

    public String getTELEFON_NR_1() {
        return TELEFON_NR_1;
    }

    public void setTELEFON_NR_1(String TELEFON_NR_1) {
        this.TELEFON_NR_1 = TELEFON_NR_1;
    }

    public String getFAX_NR_1() {
        return FAX_NR_1;
    }

    public void setFAX_NR_1(String FAX_NR_1) {
        this.FAX_NR_1 = FAX_NR_1;
    }

    public String getINFO_EMAIL() {
        return INFO_EMAIL;
    }

    public void setINFO_EMAIL(String INFO_EMAIL) {
        this.INFO_EMAIL = INFO_EMAIL;
    }

    public String getCONTACT_PERS() {
        return CONTACT_PERS;
    }

    public void setCONTACT_PERS(String CONTACT_PERS) {
        this.CONTACT_PERS = CONTACT_PERS;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getREGON() {
        return REGON;
    }

    public void setREGON(String REGON) {
        this.REGON = REGON;
    }

    public String getABC_CLASS() {
        return ABC_CLASS;
    }

    public void setABC_CLASS(String ABC_CLASS) {
        this.ABC_CLASS = ABC_CLASS;
    }

    public String getIS_ORDERER() {
        return IS_ORDERER;
    }

    public void setIS_ORDERER(String IS_ORDERER) {
        this.IS_ORDERER = IS_ORDERER;
    }

    public String getIS_SUPPLIER() {
        return IS_SUPPLIER;
    }

    public void setIS_SUPPLIER(String IS_SUPPLIER) {
        this.IS_SUPPLIER = IS_SUPPLIER;
    }

    public String getIS_CLIENT() {
        return IS_CLIENT;
    }

    public void setIS_CLIENT(String IS_CLIENT) {
        this.IS_CLIENT = IS_CLIENT;
    }

    public String getIS_CARRIER() {
        return IS_CARRIER;
    }

    public void setIS_CARRIER(String IS_CARRIER) {
        this.IS_CARRIER = IS_CARRIER;
    }

    public String getSCAN_GROUP_LABEL() {
        return SCAN_GROUP_LABEL;
    }

    public void setSCAN_GROUP_LABEL(String SCAN_GROUP_LABEL) {
        this.SCAN_GROUP_LABEL = SCAN_GROUP_LABEL;
    }
}
