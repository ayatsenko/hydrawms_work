package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Заявки", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class Requests {

    @XmlElement(name = "ЗаявкаGUID", namespace="ExchangeCRM")
    private String requestGUID;

    public String getRequestGUID() {
        return requestGUID;
    }
    public void setRequestGUID(String requestGUID) {
        this.requestGUID = requestGUID;
    }

}
