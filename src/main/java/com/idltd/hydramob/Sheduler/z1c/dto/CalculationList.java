package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокРасчеты", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculationList {

    @XmlElement(name="СтрокаРасчетов", namespace="ExchangeCRM")
    private List<CalculationString> calculationString;

}
