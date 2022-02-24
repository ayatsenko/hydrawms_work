package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_SERVICES_TEMP")
public class Load_Division {

  @Id
  @Column(name = "FIN_XML_SER_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_SERVICES_TEMP_SEQ", sequenceName = "FINANCE_XML_SERVICES_TEMP_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_SERVICES_TEMP_SEQ")
  public Long fin_xml_ser_id;

  @Column(name = "FIN_XML_SER_GUID")
  public String fin_xml_ser_guid;

  @Column(name = "FIN_XML_SER_NAME")
  public String fin_xml_ser_name;

  public Long getFin_xml_ser_id() {
    return fin_xml_ser_id;
  }

  public void setFin_xml_ser_id(Long fin_xml_ser_id) {
    this.fin_xml_ser_id = fin_xml_ser_id;
  }

  public String getFin_xml_ser_guid() {
    return fin_xml_ser_guid;
  }

  public void setFin_xml_ser_guid(String fin_xml_ser_guid) {
    this.fin_xml_ser_guid = fin_xml_ser_guid;
  }

  public String getFin_xml_ser_name() {
    return fin_xml_ser_name;
  }

  public void setFin_xml_ser_name(String fin_xml_ser_name) {
    this.fin_xml_ser_name = fin_xml_ser_name;
  }
}
