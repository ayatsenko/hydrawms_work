package com.idltd.hydramob.Sheduler.z1c.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ДанныеДтКт", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class DebCred {

    @XmlElement(name = "Дата", namespace="ExchangeCRM")
    private String date;

    @XmlElement(name="СписокПодразделения", namespace="ExchangeCRM")
    private List<SubDivisionXMLList> subdivisionXMLList;

    @XmlElement(name="СписокКонтрагенты", namespace="ExchangeCRM")
    private List<ContragentXMLList> contragentXMLList;

    @XmlElement(name="СписокДоговора", namespace="ExchangeCRM")
    private List<DealXMLList> dealXMLList;

    @XmlElement(name="СписокЗаявки", namespace="ExchangeCRM")
    private List<RequestXMLList> requestXMLList;

    @XmlElement(name="СписокСчетаПокупателей", namespace="ExchangeCRM")
    private List<BuyerAccountList> buyerAccountList;

    @XmlElement(name="СписокСчетаПоставщиков", namespace="ExchangeCRM")
    private List<SupplierAccountList> supplierAccountList;

    @XmlElement(name="СписокРасчеты", namespace="ExchangeCRM")
    private List<CalculationList> calculationList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<SubDivisionXMLList> getSubdivisionXMLList() {
        return subdivisionXMLList;
    }

    public void setSubdivisionXMLList(List<SubDivisionXMLList> subdivisionXMLList) {
        this.subdivisionXMLList = subdivisionXMLList;
    }

    public List<ContragentXMLList> getContragentXMLList() {
        return contragentXMLList;
    }

    public void setContragentXMLList(List<ContragentXMLList> contragentXMLList) {
        this.contragentXMLList = contragentXMLList;
    }
}
