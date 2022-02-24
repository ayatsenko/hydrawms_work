package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокЗаявки", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestXMLList {

    @XmlElement(name="Заявка", namespace="ExchangeCRM")
    private List<RequestXML> requestXML;

    public List<RequestXML> getRequestXML() {
        return requestXML;
    }

    public void setRequestXML(List<RequestXML> requestXML) {
        this.requestXML = requestXML;
    }
}
