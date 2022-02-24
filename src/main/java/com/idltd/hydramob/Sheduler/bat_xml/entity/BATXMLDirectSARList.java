package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SAR")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDirectSARList {
    @XmlElement(name="PRODUCT")
    private List<BATXMLDirectSARProducts> batXMLDirectSARProducts;

    public List<BATXMLDirectSARProducts> getBatXMLDirectSARProducts() {
        return batXMLDirectSARProducts;
    }

    public void setBatXMLDirectSARProducts(List<BATXMLDirectSARProducts> batXMLDirectSARProducts) {
        this.batXMLDirectSARProducts = batXMLDirectSARProducts;
    }
}
