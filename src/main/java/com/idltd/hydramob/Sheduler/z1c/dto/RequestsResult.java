package com.idltd.hydramob.Sheduler.z1c.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Заявка", namespace="ExchangeCRM")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestsResult {

    @XmlElement(name = "ЗаявкаGUID", namespace="ExchangeCRM")
    private String req_guid;

    @XmlElement(name = "АктGUID", namespace="ExchangeCRM")
    private String req_act_guid;

    @XmlElement(name = "ДатаАкта", namespace="ExchangeCRM")
    private String req_act_date;

    @XmlElement(name = "ВыручкаГрн", namespace="ExchangeCRM")
    private String req_res_procs_uah;

    @XmlElement(name = "ВыручкаEUR", namespace="ExchangeCRM")
    private String req_res_procs_eur;

    @XmlElement(name = "ЗатратыГрн", namespace="ExchangeCRM")
    private String req_res_costs_uah;

    @XmlElement(name = "ЗатратыEUR", namespace="ExchangeCRM")
    private String req_res_costs_eur;

    @XmlElement(name = "ЗатратыНДСГрн", namespace="ExchangeCRM")
    private String req_res_costs_tax_uah;

    @XmlElement(name = "ЗатратыНДСEUR", namespace="ExchangeCRM")
    private String req_res_costs_tax_eur;

    @XmlElement(name = "ПрофитГрн", namespace="ExchangeCRM")
    private String req_res_profit_uah;

    @XmlElement(name = "ПрофитEUR", namespace="ExchangeCRM")
    private String req_res_profit_eur;

    public String getReq_act_guid() {
        return req_act_guid;
    }

    public void setReq_act_guid(String req_act_guid) {
        this.req_act_guid = req_act_guid;
    }

    public String getReq_guid() {
        return req_guid;
    }

    public void setReq_guid(String req_guid) {
        this.req_guid = req_guid;
    }

    public String getReq_res_procs_uah() {
        return req_res_procs_uah;
    }

    public void setReq_res_procs_uah(String req_res_procs_uah) {
        this.req_res_procs_uah = req_res_procs_uah;
    }

    public String getReq_res_procs_eur() {
        return req_res_procs_eur;
    }

    public void setReq_res_procs_eur(String req_res_procs_eur) {
        this.req_res_procs_eur = req_res_procs_eur;
    }

    public String getReq_res_costs_uah() {
        return req_res_costs_uah;
    }

    public void setReq_res_costs_uah(String req_res_costs_uah) {
        this.req_res_costs_uah = req_res_costs_uah;
    }

    public String getReq_res_costs_eur() {
        return req_res_costs_eur;
    }

    public void setReq_res_costs_eur(String req_res_costs_eur) {
        this.req_res_costs_eur = req_res_costs_eur;
    }

    public String getReq_res_costs_tax_uah() {
        return req_res_costs_tax_uah;
    }

    public void setReq_res_costs_tax_uah(String req_res_costs_tax_uah) {
        this.req_res_costs_tax_uah = req_res_costs_tax_uah;
    }

    public String getReq_res_costs_tax_eur() {
        return req_res_costs_tax_eur;
    }

    public void setReq_res_costs_tax_eur(String req_res_costs_tax_eur) {
        this.req_res_costs_tax_eur = req_res_costs_tax_eur;
    }

    public String getReq_res_profit_uah() {
        return req_res_profit_uah;
    }

    public void setReq_res_profit_uah(String req_res_profit_uah) {
        this.req_res_profit_uah = req_res_profit_uah;
    }

    public String getReq_res_profit_eur() {
        return req_res_profit_eur;
    }

    public void setReq_res_profit_eur(String req_res_profit_eur) {
        this.req_res_profit_eur = req_res_profit_eur;
    }

    public String getReq_act_date() {
        return req_act_date;
    }

    public void setReq_act_date(String req_act_date) {
        this.req_act_date = req_act_date;
    }
}
