package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Договор", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class DealXML {

    @XmlElement(name = "GUID", namespace="ExchangeCRM")
    private String guid;

    @XmlElement(name = "Номер", namespace="ExchangeCRM")
    private String num;

    @XmlElement(name = "Дата", namespace="ExchangeCRM")
    private String date;

    @XmlElement(name = "Наименование", namespace="ExchangeCRM")
    private String name;

    @XmlElement(name = "Валюта", namespace="ExchangeCRM")
    private String currency;

    @XmlElement(name = "УсловияПлатежа", namespace="ExchangeCRM")
    private String paymentTerms;

    @XmlElement(name = "ТипДоговора", namespace="ExchangeCRM")
    private String dealType;

    @XmlElement(name = "ОтсрочкаВКалендарныхДнях", namespace="ExchangeCRM")
    private String check_PostponementInCalendarDays;

    @XmlElement(name = "МаксимальноеКоличествоДнейПросрочки", namespace="ExchangeCRM")
    private String maximumDaysDelay;

    @XmlElement(name = "МаксимальноеКоличествоДнейДоставкиДокументов", namespace="ExchangeCRM")
    private String maximumNumberOfDeliveryDaysForDocuments;

    @XmlElement(name = "РазрешенноеКоличествоДнейПросроченнойЗадолженности", namespace="ExchangeCRM")
    private String allowedNumberOfDaysOfOverdueDebt;

    @XmlElement(name = "РазрешеннаяСуммаЗадолженности", namespace="ExchangeCRM")
    private String allowedDebtAmount;

    @XmlElement(name = "КонтрагентGUID", namespace="ExchangeCRM")
    private String contragentGUID;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getCheck_PostponementInCalendarDays() {
        return check_PostponementInCalendarDays;
    }

    public void setCheck_PostponementInCalendarDays(String check_PostponementInCalendarDays) {
        this.check_PostponementInCalendarDays = check_PostponementInCalendarDays;
    }

    public String getMaximumDaysDelay() {
        return maximumDaysDelay;
    }

    public void setMaximumDaysDelay(String maximumDaysDelay) {
        this.maximumDaysDelay = maximumDaysDelay;
    }

    public String getMaximumNumberOfDeliveryDaysForDocuments() {
        return maximumNumberOfDeliveryDaysForDocuments;
    }

    public void setMaximumNumberOfDeliveryDaysForDocuments(String maximumNumberOfDeliveryDaysForDocuments) {
        this.maximumNumberOfDeliveryDaysForDocuments = maximumNumberOfDeliveryDaysForDocuments;
    }

    public String getAllowedNumberOfDaysOfOverdueDebt() {
        return allowedNumberOfDaysOfOverdueDebt;
    }

    public void setAllowedNumberOfDaysOfOverdueDebt(String allowedNumberOfDaysOfOverdueDebt) {
        this.allowedNumberOfDaysOfOverdueDebt = allowedNumberOfDaysOfOverdueDebt;
    }

    public String getAllowedDebtAmount() {
        return allowedDebtAmount;
    }

    public void setAllowedDebtAmount(String allowedDebtAmount) {
        this.allowedDebtAmount = allowedDebtAmount;
    }

    public String getContragentGUID() {
        return contragentGUID;
    }

    public void setContragentGUID(String contragentGUID) {
        this.contragentGUID = contragentGUID;
    }
}
