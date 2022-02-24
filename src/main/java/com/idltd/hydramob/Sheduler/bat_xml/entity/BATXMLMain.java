package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "РОР")
@XmlAccessorType(XmlAccessType.FIELD)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class BATXMLMain {
    @XmlElement(name="DIRECT")
    private List<BATXMLDirectList> batXMLDirectList;

    @XmlElement(name="DOC")
    private List<BATXMLDocList> batXMLDocList;

    public List<BATXMLDirectList> getBatXMLDirectList() {
        return batXMLDirectList;
    }

    public void setBatXMLDirectList(List<BATXMLDirectList> batXMLDirectList) {
        this.batXMLDirectList = batXMLDirectList;
    }

    public List<BATXMLDocList> getBatXMLDocList() {
        return batXMLDocList;
    }

    public void setBatXMLDocList(List<BATXMLDocList> batXMLDocList) {
        this.batXMLDocList = batXMLDocList;
    }
}
