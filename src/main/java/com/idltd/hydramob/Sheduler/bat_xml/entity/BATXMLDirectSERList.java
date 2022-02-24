package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SER")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDirectSERList {
    @XmlElement(name="SERLIST")
    private List<BATXMLDirectSERSerlist> batXMLDirectSERSerlist;

    public List<BATXMLDirectSERSerlist> getBatXMLDirectSERSerlist() {
        return batXMLDirectSERSerlist;
    }

    public void setBatXMLDirectSERSerlist(List<BATXMLDirectSERSerlist> batXMLDirectSERSerlist) {
        this.batXMLDirectSERSerlist = batXMLDirectSERSerlist;
    }
}
