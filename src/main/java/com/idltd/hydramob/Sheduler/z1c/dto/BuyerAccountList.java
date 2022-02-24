package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "СписокСчетаПокупателей", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuyerAccountList {

    @XmlElement(name="СчетПокупателя", namespace="ExchangeCRM")
    private List<BuyerAccount> buyerAccount;

    public List<BuyerAccount> getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(List<BuyerAccount> buyerAccount) {
        this.buyerAccount = buyerAccount;
    }
}
