package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_REQUESTS_RESULT_TEMP")
public class Load_Requests_Result {
  @Id
  @Column(name = "FIN_XML_REQ_RES_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_REQUESTS_RESUL_SEQ", sequenceName = "FINANCE_XML_REQUESTS_RESUL_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_REQUESTS_RESUL_SEQ")
  public Long fin_xml_req_res_id;

  @Column(name = "FIN_XML_REQ_GUID")
  public String fin_xml_req_guid;

  @Column(name = "FIN_XML_REQ_RES_PROCS_UAH")
  public String fin_xml_req_res_procs_uah;

  @Column(name = "FIN_XML_REQ_RES_PROCS_EUR")
  public String fin_xml_req_res_procs_eur;

  @Column(name = "FIN_XML_REQ_RES_COSTS_UAH")
  public String fin_xml_req_res_costs_uah;

  @Column(name = "FIN_XML_REQ_RES_COSTS_EUR")
  public String fin_xml_req_res_costs_eur;

  @Column(name = "FIN_XML_REQ_RES_COSTS_TAX_UAH")
  public String fin_xml_req_res_costs_tax_uah;

  @Column(name = "FIN_XML_REQ_RES_COSTS_TAX_EUR")
  public String fin_xml_req_res_costs_tax_eur;

  @Column(name = "FIN_XML_REQ_RES_PROFIT_UAH")
  public String fin_xml_req_res_profit_uah;

  @Column(name = "FIN_XML_REQ_RES_PROFIT_EUR")
  public String fin_xml_req_res_profit_eur;

  @Column(name = "FIN_XML_REQ_ACT_DATE")
  public String fin_xml_req_act_date;

  @Column(name = "FIN_XML_REQ_ACT_GUID")
  public String system_fk_id;

  public Long getFin_xml_req_res_id() {
    return fin_xml_req_res_id;
  }

  public void setFin_xml_req_res_id(Long fin_xml_req_res_id) {
    this.fin_xml_req_res_id = fin_xml_req_res_id;
  }

  public String getFin_xml_req_guid() {
    return fin_xml_req_guid;
  }

  public void setFin_xml_req_guid(String fin_xml_req_guid) {
    this.fin_xml_req_guid = fin_xml_req_guid;
  }

  public String getFin_xml_req_res_procs_uah() {
    return fin_xml_req_res_procs_uah;
  }

  public void setFin_xml_req_res_procs_uah(String fin_xml_req_res_procs_uah) {
    this.fin_xml_req_res_procs_uah = fin_xml_req_res_procs_uah;
  }

  public String getFin_xml_req_res_procs_eur() {
    return fin_xml_req_res_procs_eur;
  }

  public void setFin_xml_req_res_procs_eur(String fin_xml_req_res_procs_eur) {
    this.fin_xml_req_res_procs_eur = fin_xml_req_res_procs_eur;
  }

  public String getFin_xml_req_res_costs_uah() {
    return fin_xml_req_res_costs_uah;
  }

  public void setFin_xml_req_res_costs_uah(String fin_xml_req_res_costs_uah) {
    this.fin_xml_req_res_costs_uah = fin_xml_req_res_costs_uah;
  }

  public String getFin_xml_req_res_costs_eur() {
    return fin_xml_req_res_costs_eur;
  }

  public void setFin_xml_req_res_costs_eur(String fin_xml_req_res_costs_eur) {
    this.fin_xml_req_res_costs_eur = fin_xml_req_res_costs_eur;
  }

  public String getFin_xml_req_res_costs_tax_uah() {
    return fin_xml_req_res_costs_tax_uah;
  }

  public void setFin_xml_req_res_costs_tax_uah(String fin_xml_req_res_costs_tax_uah) {
    this.fin_xml_req_res_costs_tax_uah = fin_xml_req_res_costs_tax_uah;
  }

  public String getFin_xml_req_res_costs_tax_eur() {
    return fin_xml_req_res_costs_tax_eur;
  }

  public void setFin_xml_req_res_costs_tax_eur(String fin_xml_req_res_costs_tax_eur) {
    this.fin_xml_req_res_costs_tax_eur = fin_xml_req_res_costs_tax_eur;
  }

  public String getFin_xml_req_res_profit_uah() {
    return fin_xml_req_res_profit_uah;
  }

  public void setFin_xml_req_res_profit_uah(String fin_xml_req_res_profit_uah) {
    this.fin_xml_req_res_profit_uah = fin_xml_req_res_profit_uah;
  }

  public String getFin_xml_req_res_profit_eur() {
    return fin_xml_req_res_profit_eur;
  }

  public void setFin_xml_req_res_profit_eur(String fin_xml_req_res_profit_eur) {
    this.fin_xml_req_res_profit_eur = fin_xml_req_res_profit_eur;
  }

  public String getSystem_fk_id() {
    return system_fk_id;
  }

  public void setSystem_fk_id(String system_fk_id) {
    this.system_fk_id = system_fk_id;
  }

  public String getFin_xml_req_act_date() {
    return fin_xml_req_act_date;
  }

  public void setFin_xml_req_act_date(String fin_xml_req_act_date) {
    this.fin_xml_req_act_date = fin_xml_req_act_date;
  }
}

