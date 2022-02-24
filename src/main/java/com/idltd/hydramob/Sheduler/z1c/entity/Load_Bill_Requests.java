package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_BILLS_REQ_TEMP")
public class Load_Bill_Requests {
  @Id
  @Column(name = "FIN_XML_BILL_REQ_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_BILLS_REQ_TEMP_SEQ", sequenceName = "FINANCE_XML_BILLS_REQ_TEMP_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_BILLS_REQ_TEMP_SEQ")
  public Long fin_xml_bill_req_id;

  @Column(name = "FIN_XML_BILL_GUID")
  public String fin_xml_bill_guid;

  @Column(name = "FIN_XML_REQ_GUID")
  public String fin_xml_req_guid;

  public Long getFin_xml_bill_req_id() {
    return fin_xml_bill_req_id;
  }

  public void setFin_xml_bill_req_id(Long fin_xml_bill_req_id) {
    this.fin_xml_bill_req_id = fin_xml_bill_req_id;
  }

  public String getFin_xml_bill_guid() {
    return fin_xml_bill_guid;
  }

  public void setFin_xml_bill_guid(String fin_xml_bill_guid) {
    this.fin_xml_bill_guid = fin_xml_bill_guid;
  }

  public String getFin_xml_req_guid() {
    return fin_xml_req_guid;
  }

  public void setFin_xml_req_guid(String fin_xml_req_guid) {
    this.fin_xml_req_guid = fin_xml_req_guid;
  }
}