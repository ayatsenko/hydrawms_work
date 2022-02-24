package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "СтрокаРасчетов", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculationString {

    @XmlElement(name = "ТипРасчетов", namespace="ExchangeCRM")
    private String calculationType;

    @XmlElement(name = "СтатусРасчетов", namespace="ExchangeCRM")
    private String calculationStatus;

    @XmlElement(name = "ПодразделениеGUID", namespace="ExchangeCRM")
    private String subdivisionGUID;

    @XmlElement(name = "КонтрагентGUID", namespace="ExchangeCRM")
    private String contragentGUID;

    @XmlElement(name = "ДоговорGUID", namespace="ExchangeCRM")
    private String dealGUID;

    @XmlElement(name = "СчетGUID", namespace="ExchangeCRM")
    private String accountGUID;

    @XmlElement(name = "ДатаОплатыПлан", namespace="ExchangeCRM")
    private String datePayPlan;

    @XmlElement(name = "Валюта", namespace="ExchangeCRM")
    private String currency;

    @XmlElement(name = "СуммаВал", namespace="ExchangeCRM")
    private String sumval;

    @XmlElement(name = "СуммаГрн", namespace="ExchangeCRM")
    private String sumgrn;

    @XmlElement(name = "ПросроченоДней", namespace="ExchangeCRM")
    private String overdueDays;

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public String getCalculationStatus() {
        return calculationStatus;
    }

    public void setCalculationStatus(String calculationStatus) {
        this.calculationStatus = calculationStatus;
    }

    public String getSubdivisionGUID() {
        return subdivisionGUID;
    }

    public void setSubdivisionGUID(String subdivisionGUID) {
        this.subdivisionGUID = subdivisionGUID;
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

    public String getAccountGUID() {
        return accountGUID;
    }

    public void setAccountGUID(String accountGUID) {
        this.accountGUID = accountGUID;
    }

    public String getDatePayPlan() {
        return datePayPlan;
    }

    public void setDatePayPlan(String datePayPlan) {
        this.datePayPlan = datePayPlan;
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

    public String getSumgrn() {
        return sumgrn;
    }

    public void setSumgrn(String sumgrn) {
        this.sumgrn = sumgrn;
    }

    public String getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(String overdueDays) {
        this.overdueDays = overdueDays;
    }
}
