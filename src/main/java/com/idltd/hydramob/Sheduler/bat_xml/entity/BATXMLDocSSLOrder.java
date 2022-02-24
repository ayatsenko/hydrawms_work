package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDocSSLOrder {
    @XmlElement(name = "RECTYP")
    @Column(name = "RECTYP")
    private String rectyp;

    @XmlElement(name = "FIRM_NR")
    @Column(name = "FIRM_NR")
    private String firm_nr;

    @XmlElement(name = "ORDER_NR")
    @Column(name = "ORDER_NR")
    private String order_nr;

    @XmlElement(name = "CUSTOMER_NR")
    @Column(name = "CUSTOMER_NR")
    private String customer_nr;

    @XmlElement(name = "DATE_DELIV")
    @Column(name = "DATE_DELIV")
    private String date_deliv;

    @XmlElement(name = "ADR_STREET")
    @Column(name = "ADR_STREET")
    private String adr_street;

    @XmlElement(name = "ADR_CITY")
    @Column(name = "ADR_CITY")
    private String adr_city;

    @XmlElement(name = "C_ORDER_NR")
    @Column(name = "C_ORDER_NR")
    private String c_order_nr;

    @XmlElement(name = "WH_NR")
    @Column(name = "WH_NR")
    private String wh_nr;

    @XmlElement(name = "DATE_CREATED")
    @Column(name = "DATE_CREATED")
    private String date_created;

    @XmlElement(name = "DATE_ORDERED")
    @Column(name = "DATE_ORDERED")
    private String date_ordered;

    @XmlElement(name = "INFO_1")
    @Column(name = "INFO_1")
    private String info_1;

    @XmlElement(name = "ADR_ZIPCODE")
    @Column(name = "ADR_ZIPCODE")
    private String adr_zipcode;

    @XmlElement(name = "ADR_POSTBOX")
    @Column(name = "ADR_POSTBOX")
    private String adr_postbox;

    @XmlElement(name = "ADR_COUNTRY_CODE")
    @Column(name = "ADR_COUNTRY_CODE")
    private String adr_country_code;

    @XmlElement(name="ITEM")
    private List<BATXMLDocSSLOrderItem> batXMLDocSSLOrderItem;

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getFirm_nr() {
        return firm_nr;
    }

    public void setFirm_nr(String firm_nr) {
        this.firm_nr = firm_nr;
    }

    public String getOrder_nr() {
        return order_nr;
    }

    public void setOrder_nr(String order_nr) {
        this.order_nr = order_nr;
    }

    public String getCustomer_nr() {
        return customer_nr;
    }

    public void setCustomer_nr(String customer_nr) {
        this.customer_nr = customer_nr;
    }

    public String getDate_deliv() {
        return date_deliv;
    }

    public void setDate_deliv(String date_deliv) {
        this.date_deliv = date_deliv;
    }

    public String getAdr_street() {
        return adr_street;
    }

    public void setAdr_street(String adr_street) {
        this.adr_street = adr_street;
    }

    public String getAdr_city() {
        return adr_city;
    }

    public void setAdr_city(String adr_city) {
        this.adr_city = adr_city;
    }

    public String getC_order_nr() {
        return c_order_nr;
    }

    public void setC_order_nr(String c_order_nr) {
        this.c_order_nr = c_order_nr;
    }

    public String getWh_nr() {
        return wh_nr;
    }

    public void setWh_nr(String wh_nr) {
        this.wh_nr = wh_nr;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_ordered() {
        return date_ordered;
    }

    public void setDate_ordered(String date_ordered) {
        this.date_ordered = date_ordered;
    }

    public String getInfo_1() {
        return info_1;
    }

    public void setInfo_1(String info_1) {
        this.info_1 = info_1;
    }

    public String getAdr_zipcode() {
        return adr_zipcode;
    }

    public void setAdr_zipcode(String adr_zipcode) {
        this.adr_zipcode = adr_zipcode;
    }

    public String getAdr_postbox() {
        return adr_postbox;
    }

    public void setAdr_postbox(String adr_postbox) {
        this.adr_postbox = adr_postbox;
    }

    public String getAdr_country_code() {
        return adr_country_code;
    }

    public void setAdr_country_code(String adr_country_code) {
        this.adr_country_code = adr_country_code;
    }

    public List<BATXMLDocSSLOrderItem> getBatXMLDocSSLOrderItem() {
        return batXMLDocSSLOrderItem;
    }

    public void setBatXMLDocSSLOrderItem(List<BATXMLDocSSLOrderItem> batXMLDocSSLOrderItem) {
        this.batXMLDocSSLOrderItem = batXMLDocSSLOrderItem;
    }
}
