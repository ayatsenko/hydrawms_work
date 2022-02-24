package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Заявка", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestXML {

    @XmlElement(name = "GUID", namespace="ExchangeCRM")
    private String guid;

    @XmlElement(name = "Номер", namespace="ExchangeCRM")
    private String num;

    @XmlElement(name = "Дата", namespace="ExchangeCRM")
    private String date;

    @XmlElement(name = "Валюта", namespace="ExchangeCRM")
    private String currency;

    @XmlElement(name = "СуммаВал", namespace="ExchangeCRM")
    private String sumval;

    @XmlElement(name = "КонтрагентGUID", namespace="ExchangeCRM")
    private String contragentGUID;

    @XmlElement(name = "ДоговорGUID", namespace="ExchangeCRM")
    private String dealGUID;

    @XmlElement(name = "ПодразделениеGUID", namespace="ExchangeCRM")
    private String subdivisionGUID;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSumval() {
        return sumval;
    }

    public void setSumval(String sumval) {
        this.sumval = sumval;
    }

    public String getContragentGUID() {
        return contragentGUID;
    }

    public void setContragentGUID(String contragentGUID) {
        this.contragentGUID = contragentGUID;
    }

    public String getDealGUID() {
        return dealGUID;
    }

    public void setDealGUID(String dealGUID) {
        this.dealGUID = dealGUID;
    }

    public String getSubdivisionGUID() {
        return subdivisionGUID;
    }

    public void setSubdivisionGUID(String subdivisionGUID) {
        this.subdivisionGUID = subdivisionGUID;
    }
}
