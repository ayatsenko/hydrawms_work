package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_BILLS_TEMP")
public class Load_Bills {
  @Id
  @Column(name = "FIN_XML_BILL_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_BILLS_TEMP_SEQ", sequenceName = "FINANCE_XML_BILLS_TEMP_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_BILLS_TEMP_SEQ")
  public Long fin_xml_bill_id;

  @Column(name = "FIN_XML_BILL_GUID")
  public String fin_xml_bill_guid;

  @Column(name = "FIN_XML_BILL_NUM")
  public String fin_xml_bill_num;

  @Column(name = "FIN_XML_BILL_DATE")
  public String fin_xml_bill_date;

  @Column(name = "FIN_XML_BILL_ACT_DATE")
  public String fin_xml_bill_act_date;

  @Column(name = "FIN_XML_BILL_PLAN_DATE")
  public String fin_xml_bill_plan_date;

  @Column(name = "FIN_XML_BILL_CUR")
  public String fin_xml_bill_cur;

  @Column(name = "FIN_XML_BILL_CUR_SUM")
  public String fin_xml_bill_cur_sum;

  @Column(name = "FIN_XML_BILL_SUM")
  public String fin_xml_bill_sum;

  @Column(name = "FIN_XML_CLIENT_GUID")
  public String fin_xml_client_guid;

  @Column(name = "FIN_XML_DOC_GUID")
  public String fin_xml_doc_guid;

  public Long getFin_xml_bill_id() {
    return fin_xml_bill_id;
  }

  public void setFin_xml_bill_id(Long fin_xml_bill_id) {
    this.fin_xml_bill_id = fin_xml_bill_id;
  }

  public String getFin_xml_bill_guid() {
    return fin_xml_bill_guid;
  }

  public void setFin_xml_bill_guid(String fin_xml_bill_guid) {
    this.fin_xml_bill_guid = fin_xml_bill_guid;
  }

  public String getFin_xml_bill_num() {
    return fin_xml_bill_num;
  }

  public void setFin_xml_bill_num(String fin_xml_bill_num) {
    this.fin_xml_bill_num = fin_xml_bill_num;
  }

  public String getFin_xml_bill_date() {
    return fin_xml_bill_date;
  }

  public void setFin_xml_bill_date(String fin_xml_bill_date) {
    this.fin_xml_bill_date = fin_xml_bill_date;
  }

  public String getFin_xml_bill_act_date() {
    return fin_xml_bill_act_date;
  }

  public void setFin_xml_bill_act_date(String fin_xml_bill_act_date) {
    this.fin_xml_bill_act_date = fin_xml_bill_act_date;
  }

  public String getFin_xml_bill_plan_date() {
    return fin_xml_bill_plan_date;
  }

  public void setFin_xml_bill_plan_date(String fin_xml_bill_plan_date) {
    this.fin_xml_bill_plan_date = fin_xml_bill_plan_date;
  }

  public String getFin_xml_bill_cur() {
    return fin_xml_bill_cur;
  }

  public void setFin_xml_bill_cur(String fin_xml_bill_cur) {
    this.fin_xml_bill_cur = fin_xml_bill_cur;
  }

  public String getFin_xml_bill_cur_sum() {
    return fin_xml_bill_cur_sum;
  }

  public void setFin_xml_bill_cur_sum(String fin_xml_bill_cur_sum) {
    this.fin_xml_bill_cur_sum = fin_xml_bill_cur_sum;
  }

  public String getFin_xml_bill_sum() {
    return fin_xml_bill_sum;
  }

  public void setFin_xml_bill_sum(String fin_xml_bill_sum) {
    this.fin_xml_bill_sum = fin_xml_bill_sum;
  }

  public String getFin_xml_client_guid() {
    return fin_xml_client_guid;
  }

  public void setFin_xml_client_guid(String fin_xml_client_guid) {
    this.fin_xml_client_guid = fin_xml_client_guid;
  }

  public String getFin_xml_doc_guid() {
    return fin_xml_doc_guid;
  }

  public void setFin_xml_doc_guid(String fin_xml_doc_guid) {
    this.fin_xml_doc_guid = fin_xml_doc_guid;
  }
}