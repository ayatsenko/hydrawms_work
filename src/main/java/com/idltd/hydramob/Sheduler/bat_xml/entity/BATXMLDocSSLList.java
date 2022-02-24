package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SSL")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDocSSLList {
    @XmlElement(name="ORDER")
    private List<BATXMLDocSSLOrderList> batXMLDocSSLOrderList;

    public List<BATXMLDocSSLOrderList> getBatXMLDocSSLOrderList() {
        return batXMLDocSSLOrderList;
    }

    public void setBatXMLDocSSLOrderList(List<BATXMLDocSSLOrderList> batXMLDocSSLOrderList) {
        this.batXMLDocSSLOrderList = batXMLDocSSLOrderList;
    }
}
