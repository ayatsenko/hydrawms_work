package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокСчетаПоставщиков", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierAccountList {

    @XmlElement(name="СчетПоставщика", namespace="ExchangeCRM")
    private List<SupplierAccount> supplierAccount;

}
