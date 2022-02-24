package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ПлатежПокупателя", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPayment {

    @XmlElement(name = "СчетПокупателяGUID", namespace="ExchangeCRM")
    private String billguid;

    @XmlElement(name = "Дата", namespace="ExchangeCRM")
    private String date;

    @XmlElement(name = "Валюта", namespace="ExchangeCRM")
    private String currency;

    @XmlElement(name = "СуммаВал", namespace="ExchangeCRM")
    private String sumval;

    public String getBillguid() {
        return billguid;
    }

    public void setBillguid(String billguid) {
        this.billguid = billguid;
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
}
