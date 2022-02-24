package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокКонтрагенты", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContragentXMLList {

    @XmlElement(name="Контрагент", namespace="ExchangeCRM")
    private List<ContragentXML> contragnet;

    public List<ContragentXML> getContragnet() {
        return contragnet;
    }

    public void setContragnet(List<ContragentXML> contragnet) {
        this.contragnet = contragnet;
    }
}
