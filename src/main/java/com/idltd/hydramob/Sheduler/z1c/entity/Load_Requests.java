package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_REQUESTS_TEMP")
public class Load_Requests {
  @Id
  @Column(name = "FIN_XML_REQ_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_REQUESTS_TEMP_SEQ", sequenceName = "FINANCE_XML_REQUESTS_TEMP_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_REQUESTS_TEMP_SEQ")
  public Long fin_xml_req_id;

  @Column(name = "FIN_XML_REQ_GUID")
  public String fin_xml_req_guid;

  @Column(name = "FIN_XML_REQ_NUM")
  public String fin_xml_req_num;

  @Column(name = "FIN_XML_REQ_DATE")
  public String fin_xml_req_date;

  @Column(name = "FIN_XML_REQ_CUR")
  public String fin_xml_req_cur;

  @Column(name = "FIN_XML_REQ_CUR_SUM")
  public String fin_xml_req_cur_sum;

  @Column(name = "FIN_XML_REQ_SUM")
  public String fin_xml_req_sum;

  @Column(name = "FIN_XML_CLIENT_GUID")
  public String fin_xml_client_guid;

  @Column(name = "FIN_XML_DOC_GUID")
  public String fin_xml_doc_guid;

  @Column(name = "FIN_XML_SER_GUID")
  public String fin_xml_ser_guid;

  public Long getFin_xml_req_id() {
    return fin_xml_req_id;
  }

  public void setFin_xml_req_id(Long fin_xml_req_id) {
    this.fin_xml_req_id = fin_xml_req_id;
  }

  public String getFin_xml_req_guid() {
    return fin_xml_req_guid;
  }

  public void setFin_xml_req_guid(String fin_xml_req_guid) {
    this.fin_xml_req_guid = fin_xml_req_guid;
  }

  public String getFin_xml_req_num() {
    return fin_xml_req_num;
  }

  public void setFin_xml_req_num(String fin_xml_req_num) {
    this.fin_xml_req_num = fin_xml_req_num;
  }

  public String getFin_xml_req_date() {
    return fin_xml_req_date;
  }

  public void setFin_xml_req_date(String fin_xml_req_date) {
    this.fin_xml_req_date = fin_xml_req_date;
  }

  public String getFin_xml_req_cur() {
    return fin_xml_req_cur;
  }

  public void setFin_xml_req_cur(String fin_xml_req_cur) {
    this.fin_xml_req_cur = fin_xml_req_cur;
  }

  public String getFin_xml_req_cur_sum() {
    return fin_xml_req_cur_sum;
  }

  public void setFin_xml_req_cur_sum(String fin_xml_req_cur_sum) {
    this.fin_xml_req_cur_sum = fin_xml_req_cur_sum;
  }

  public String getFin_xml_req_sum() {
    return fin_xml_req_sum;
  }

  public void setFin_xml_req_sum(String fin_xml_req_sum) {
    this.fin_xml_req_sum = fin_xml_req_sum;
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

  public String getFin_xml_ser_guid() {
    return fin_xml_ser_guid;
  }

  public void setFin_xml_ser_guid(String fin_xml_ser_guid) {
    this.fin_xml_ser_guid = fin_xml_ser_guid;
  }
}

