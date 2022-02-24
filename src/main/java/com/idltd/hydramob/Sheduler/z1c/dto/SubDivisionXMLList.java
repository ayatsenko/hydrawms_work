package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокПодразделения", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubDivisionXMLList {

    @XmlElement(name="Подразделение", namespace="ExchangeCRM")
    private List<SubDivisionXML> subDivisionXML;

    public List<SubDivisionXML> getSubdivision() {
        return subDivisionXML;
    }

    public void setSubdivision(List<SubDivisionXML> subdivision) {
        this.subDivisionXML = subdivision;
    }
}
