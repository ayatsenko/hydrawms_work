package com.idltd.hydramob.Sheduler.z1c.finance;


import com.idltd.hydramob.Sheduler.z1c.dto.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ДанныеСчетаПокупателей", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancePayments {
    @XmlElement(name="СписокПодразделения", namespace="ExchangeCRM")
    private List<SubDivisionXMLList> subdivisionXMLList;

    @XmlElement(name="СписокКонтрагенты", namespace="ExchangeCRM")
    private List<ContragentXMLList> contragentXMLList;

    @XmlElement(name="СписокДоговора", namespace="ExchangeCRM")
    private List<DealXMLList> dealXMLList;

    @XmlElement(name="СписокЗаявки", namespace="ExchangeCRM")
    private List<RequestXMLList> requestXMLList;

    @XmlElement(name="СписокСчетаПокупателей", namespace="ExchangeCRM")
    private List<BuyerAccountList> buyerAccountList;

    @XmlElement(name="СписокПлатежиПокупателей", namespace="ExchangeCRM")
    private List<CustomerPaymentList> customerPaymentList;

    @XmlElement(name="СписокФинПоказатели", namespace="ExchangeCRM")
    private List<RequestsResultList> requestsResultList;

    public List<SubDivisionXMLList> getSubdivisionXMLList() {
        return subdivisionXMLList;
    }

    public void setSubdivisionXMLList(List<SubDivisionXMLList> subdivisionXMLList) {
        this.subdivisionXMLList = subdivisionXMLList;
    }

    public List<ContragentXMLList> getContragentXMLList() {
        return contragentXMLList;
    }

    public void setContragentXMLList(List<ContragentXMLList> contragentXMLList) {
        this.contragentXMLList = contragentXMLList;
    }

    public List<DealXMLList> getDealXMLList() {
        return dealXMLList;
    }

    public void setDealXMLList(List<DealXMLList> dealXMLList) {
        this.dealXMLList = dealXMLList;
    }

    public List<RequestXMLList> getRequestXMLList() {
        return requestXMLList;
    }

    public void setRequestXMLList(List<RequestXMLList> requestXMLList) {
        this.requestXMLList = requestXMLList;
    }

    public List<BuyerAccountList> getBuyerAccountList() {
        return buyerAccountList;
    }

    public void setBuyerAccountList(List<BuyerAccountList> buyerAccountList) {
        this.buyerAccountList = buyerAccountList;
    }

    public List<CustomerPaymentList> getCustomerPaymentList() {
        return customerPaymentList;
    }

    public void setCustomerPaymentList(List<CustomerPaymentList> customerPaymentList) {
        this.customerPaymentList = customerPaymentList;
    }

    public List<RequestsResultList> getRequestsResultList() {
        return requestsResultList;
    }

    public void setRequestsResultList(List<RequestsResultList> requestsResultList) {
        this.requestsResultList = requestsResultList;
    }
}
