package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "DIRECT")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDirectList {
    @XmlElement(name="SKH")
    private List<BATXMLDirectSKHList> batXMLDirectSKHList;

    @XmlElement(name="SER")
    private List<BATXMLDirectSERList> batXMLDirectSERList;

    @XmlElement(name="SAR")
    private List<BATXMLDirectSARList> batXMLDirectSARList;

    public List<BATXMLDirectSKHList> getBatXMLDirectSKHList() {
        return batXMLDirectSKHList;
    }

    public void setBatXMLDirectSKHList(List<BATXMLDirectSKHList> batXMLDirectSKHList) {
        this.batXMLDirectSKHList = batXMLDirectSKHList;
    }

    public List<BATXMLDirectSERList> getBatXMLDirectSERList() {
        return batXMLDirectSERList;
    }

    public void setBatXMLDirectSERList(List<BATXMLDirectSERList> batXMLDirectSERList) {
        this.batXMLDirectSERList = batXMLDirectSERList;
    }

    public List<BATXMLDirectSARList> getBatXMLDirectSARList() {
        return batXMLDirectSARList;
    }

    public void setBatXMLDirectSARList(List<BATXMLDirectSARList> batXMLDirectSARList) {
        this.batXMLDirectSARList = batXMLDirectSARList;
    }
}
