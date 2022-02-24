package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокДоговора", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class DealXMLList {

    @XmlElement(name="Договор", namespace="ExchangeCRM")
    private List<DealXML> deal;

    public List<DealXML> getDeal() {
        return deal;
    }

    public void setDeal(List<DealXML> deal) {
        this.deal = deal;
    }
}
