package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SKH")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDirectSKHList {
    @XmlElement(name="CUSTOMER")
    private List<BATXMLDirectSKHCustomer> batXMLDirectSKHCustomer;

    public List<BATXMLDirectSKHCustomer> getBatXMLDirectSKHCustomer() {
        return batXMLDirectSKHCustomer;
    }

    public void setBatXMLDirectSKHCustomer(List<BATXMLDirectSKHCustomer> batXMLDirectSKHCustomer) {
        this.batXMLDirectSKHCustomer = batXMLDirectSKHCustomer;
    }
}
