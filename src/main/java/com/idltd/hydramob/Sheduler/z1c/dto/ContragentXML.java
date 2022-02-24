package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Контрагент", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContragentXML {

    @XmlElement(name = "GUID", namespace="ExchangeCRM")
    private String guid;

    @XmlElement(name = "Наименование", namespace="ExchangeCRM")
    private String name;

    @XmlElement(name = "ЕДРПОУ", namespace="ExchangeCRM")
    private String edrpou;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(String edrpou) {
        this.edrpou = edrpou;
    }
}
