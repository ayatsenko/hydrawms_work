package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "DOC")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDocList {
    @XmlElement(name="SSL")
    private List<BATXMLDocSSLList> batXMLDocSSLList;

    public List<BATXMLDocSSLList> getBatXMLDocSSLList() {
        return batXMLDocSSLList;
    }

    public void setBatXMLDocSSLList(List<BATXMLDocSSLList> batXMLDocSSLList) {
        this.batXMLDocSSLList = batXMLDocSSLList;
    }
}
