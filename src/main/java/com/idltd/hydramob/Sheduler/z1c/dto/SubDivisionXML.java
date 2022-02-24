package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Подразделение", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubDivisionXML {

    @XmlElement(name = "GUID", namespace = "ExchangeCRM")
    @Column(name = "FIN_XML_SER_GUID")
    private String guid;

    @XmlElement(name = "Наименование", namespace = "ExchangeCRM")
    @Column(name = "FIN_XML_SER_NAME")
    private String name;

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
}
