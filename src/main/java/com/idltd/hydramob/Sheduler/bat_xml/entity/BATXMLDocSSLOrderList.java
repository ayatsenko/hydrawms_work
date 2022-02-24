package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ORDER")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDocSSLOrderList {
    @XmlElement(name="HEADER")
    private List<BATXMLDocSSLOrder> batXMLDocSSLOrder;

    public List<BATXMLDocSSLOrder> getBatXMLDocSSLOrder() {
        return batXMLDocSSLOrder;
    }

    public void setBatXMLDocSSLOrder(List<BATXMLDocSSLOrder> batXMLDocSSLOrder) {
        this.batXMLDocSSLOrder = batXMLDocSSLOrder;
    }
}
