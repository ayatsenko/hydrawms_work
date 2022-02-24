package com.idltd.hydramob.quguar.export.spr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
public class SprHeader {
    @XmlElement(required =true)
    private String RECTYP = "";
    @XmlElement(required =true)
    private String FIRM_NR = "";
    @XmlElement(required =true)
    private String ORDER_NR = "";
    @XmlElement(required =true)
    private String CUSTOMER_NR = "";
    @XmlElement(required =true)
    private String C_ORDER_NR = "";
    @XmlElement(required =true)
    private String WH_NR = "";
    @XmlElement(required =true)
    private String DATE_DELIV = "";
    @XmlElement(required =true)
    private String DATE_SHIPPING = "";
    @XmlElement(required =true)
    private String DATE_CREATED = "";
    @XmlElement(required =true)
    private String DATE_ORDERED = "";
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

    @XmlElement(name = "ITEM", required =true)
    private List<SprItem> sprItemList;

    public SprHeader() {
        this.sprItemList = new ArrayList<>();
    }

    public void addItem(SprItem spritem) {
        this.sprItemList.add(spritem);
    }
    public List<SprItem> getSprItemList() {
        return sprItemList;
    }

    public void setSprItemList(List<SprItem> sprItemList) {
        this.sprItemList = sprItemList;
    }

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

    public String getORDER_NR() {
        return ORDER_NR;
    }

    public void setORDER_NR(String ORDER_NR) {
        this.ORDER_NR = ORDER_NR;
    }

    public String getCUSTOMER_NR() {
        return CUSTOMER_NR;
    }

    public void setCUSTOMER_NR(String CUSTOMER_NR) {
        this.CUSTOMER_NR = CUSTOMER_NR;
    }

    public String getC_ORDER_NR() {
        return C_ORDER_NR;
    }

    public void setC_ORDER_NR(String c_ORDER_NR) {
        C_ORDER_NR = c_ORDER_NR;
    }

    public String getWH_NR() {
        return WH_NR;
    }

    public void setWH_NR(String WH_NR) {
        this.WH_NR = WH_NR;
    }

    public String getDATE_DELIV() {
        return DATE_DELIV;
    }

    public void setDATE_DELIV(String DATE_DELIV) {
        this.DATE_DELIV = DATE_DELIV;
    }

    public String getDATE_SHIPPING() {
        return DATE_SHIPPING;
    }

    public void setDATE_SHIPPING(String DATE_SHIPPING) {
        this.DATE_SHIPPING = DATE_SHIPPING;
    }

    public String getDATE_CREATED() {
        return DATE_CREATED;
    }

    public void setDATE_CREATED(String DATE_CREATED) {
        this.DATE_CREATED = DATE_CREATED;
    }

    public String getDATE_ORDERED() {
        return DATE_ORDERED;
    }

    public void setDATE_ORDERED(String DATE_ORDERED) {
        this.DATE_ORDERED = DATE_ORDERED;
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
}
