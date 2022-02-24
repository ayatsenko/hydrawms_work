package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Заявки", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillRequestXMLList {
    @XmlElement(name = "ЗаявкаGUID", namespace="ExchangeCRM")
    private List<String> requestGUID;

    public List<String> getRequestGUID() {
        return requestGUID;
    }

    public void setRequestGUID(List<String> requestGUID) {
        this.requestGUID = requestGUID;
    }
}
