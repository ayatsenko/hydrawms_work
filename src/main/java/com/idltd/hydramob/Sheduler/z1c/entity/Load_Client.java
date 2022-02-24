package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_CLIENTS_TEMP")
public class Load_Client {

  @Id
  @Column(name = "FIN_XML_CLIENT_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_CLIENTS_TEMP_SEQ", sequenceName = "FINANCE_XML_CLIENTS_TEMP_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_CLIENTS_TEMP_SEQ")
  public Long fin_xml_client_id;

  @Column(name = "FIN_XML_CLIENT_GUID")
  public String fin_xml_client_guid;

  @Column(name = "FIN_XML_CLIENT_NAME")
  public String fin_xml_client_name;

  @Column(name = "FIN_XML_CLIENT_INN")
  public String fin_xml_client_inn;

  public Long getFin_xml_client_id() {
    return fin_xml_client_id;
  }

  public void setFin_xml_client_id(Long fin_xml_client_id) {
    this.fin_xml_client_id = fin_xml_client_id;
  }

  public String getFin_xml_client_guid() {
    return fin_xml_client_guid;
  }

  public void setFin_xml_client_guid(String fin_xml_client_guid) {
    this.fin_xml_client_guid = fin_xml_client_guid;
  }

  public String getFin_xml_client_name() {
    return fin_xml_client_name;
  }

  public void setFin_xml_client_name(String fin_xml_client_name) {
    this.fin_xml_client_name = fin_xml_client_name;
  }

  public String getFin_xml_client_inn() {
    return fin_xml_client_inn;
  }

  public void setFin_xml_client_inn(String fin_xml_client_inn) {
    this.fin_xml_client_inn = fin_xml_client_inn;
  }
}
