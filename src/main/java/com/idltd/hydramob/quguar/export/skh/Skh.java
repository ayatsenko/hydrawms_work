package com.idltd.hydramob.quguar.export.skh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SKH")
@XmlAccessorType(XmlAccessType.FIELD)
public class Skh {
    @XmlElement(name = "CUSTOMER", required =true)
    private List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void addCustomer(Customer customer) {
        this.customerList.add(customer);
    }

    public Skh() {
        this.customerList = new ArrayList<>();
    }
}
