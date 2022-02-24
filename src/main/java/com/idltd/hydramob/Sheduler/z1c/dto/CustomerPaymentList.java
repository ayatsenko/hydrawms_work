package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокПлатежиПокупателей", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPaymentList {

    @XmlElement(name="ПлатежПокупателя", namespace="ExchangeCRM")
    private List<CustomerPayment> customerPayment;

    public List<CustomerPayment> getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(List<CustomerPayment> customerPayment) {
        this.customerPayment = customerPayment;
    }
}
