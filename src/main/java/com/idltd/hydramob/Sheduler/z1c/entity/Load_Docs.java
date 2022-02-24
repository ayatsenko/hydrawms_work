package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_DOCS_TEMP")
public class Load_Docs {

  @Id
  @Column(name = "FIN_XML_DOC_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_DOCS_TEMP_SEQ", sequenceName = "FINANCE_XML_DOCS_TEMP_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_DOCS_TEMP_SEQ")
  public Long fin_xml_doc_id;

  @Column(name = "FIN_XML_DOC_GUID")
  public String fin_xml_doc_guid;

  @Column(name = "FIN_XML_DOC_NUM")
  public String fin_xml_doc_num;

  @Column(name = "FIN_XML_DOC_DATE")
  public String fin_xml_doc_date;

  @Column(name = "FIN_XML_DOC_NAME")
  public String fin_xml_doc_name;

  @Column(name = "FIN_XML_DOC_CURR")
  public String fin_xml_doc_curr;

  @Column(name = "FIN_XML_DOC_TERMS")
  public String fin_xml_doc_terms;

  @Column(name = "FIN_XML_DOC_TYPE")
  public String fin_xml_doc_type;

  @Column(name = "FIN_XML_DOC_DELAY")
  public String fin_xml_doc_delay;

  @Column(name = "FIN_XML_DOC_MAXDELAY")
  public String fin_xml_doc_maxdelay;

  @Column(name = "FIN_XML_DOC_DOCDELAY")
  public String fin_xml_doc_docdelay;

  @Column(name = "FIN_XML_CLIENT_GUID")
  public String fin_xml_client_guid;

  @Column(name = "FIN_XML_DOC_SUMDELAY")
  public String fin_xml_doc_sumdelay;

  @Column(name = "FIN_XML_DOC_UPPSUM")
  public String fin_xml_doc_uppsum;

  public String getFin_xml_doc_guid() {
    return fin_xml_doc_guid;
  }

  public void setFin_xml_doc_guid(String fin_xml_doc_guid) {
    this.fin_xml_doc_guid = fin_xml_doc_guid;
  }

  public String getFin_xml_doc_num() {
    return fin_xml_doc_num;
  }

  public void setFin_xml_doc_num(String fin_xml_doc_num) {
    this.fin_xml_doc_num = fin_xml_doc_num;
  }

  public String getFin_xml_doc_date() {
    return fin_xml_doc_date;
  }

  public void setFin_xml_doc_date(String fin_xml_doc_date) {
    this.fin_xml_doc_date = fin_xml_doc_date;
  }

  public String getFin_xml_doc_name() {
    return fin_xml_doc_name;
  }

  public void setFin_xml_doc_name(String fin_xml_doc_name) {
    this.fin_xml_doc_name = fin_xml_doc_name;
  }

  public String getFin_xml_doc_curr() {
    return fin_xml_doc_curr;
  }

  public void setFin_xml_doc_curr(String fin_xml_doc_curr) {
    this.fin_xml_doc_curr = fin_xml_doc_curr;
  }

  public String getFin_xml_doc_terms() {
    return fin_xml_doc_terms;
  }

  public void setFin_xml_doc_terms(String fin_xml_doc_terms) {
    this.fin_xml_doc_terms = fin_xml_doc_terms;
  }

  public String getFin_xml_doc_type() {
    return fin_xml_doc_type;
  }

  public void setFin_xml_doc_type(String fin_xml_doc_type) {
    this.fin_xml_doc_type = fin_xml_doc_type;
  }

  public String getFin_xml_doc_delay() {
    return fin_xml_doc_delay;
  }

  public void setFin_xml_doc_delay(String fin_xml_doc_delay) {
    this.fin_xml_doc_delay = fin_xml_doc_delay;
  }

  public String getFin_xml_doc_maxdelay() {
    return fin_xml_doc_maxdelay;
  }

  public void setFin_xml_doc_maxdelay(String fin_xml_doc_maxdelay) {
    this.fin_xml_doc_maxdelay = fin_xml_doc_maxdelay;
  }


  public String getFin_xml_doc_docdelay() {
    return fin_xml_doc_docdelay;
  }

  public void setFin_xml_doc_docdelay(String fin_xml_doc_docdelay) {
    this.fin_xml_doc_docdelay = fin_xml_doc_docdelay;
  }

  public String getFin_xml_client_guid() {
    return fin_xml_client_guid;
  }

  public void setFin_xml_client_guid(String fin_xml_client_guid) {
    this.fin_xml_client_guid = fin_xml_client_guid;
  }

  public String getFin_xml_doc_sumdelay() {
    return fin_xml_doc_sumdelay;
  }

  public void setFin_xml_doc_sumdelay(String fin_xml_doc_sumdelay) {
    this.fin_xml_doc_sumdelay = fin_xml_doc_sumdelay;
  }

  public String getFin_xml_doc_uppsum() {
    return fin_xml_doc_uppsum;
  }

  public void setFin_xml_doc_uppsum(String fin_xml_doc_uppsum) {
    this.fin_xml_doc_uppsum = fin_xml_doc_uppsum;
  }
}
