package com.idltd.hydramob.Sheduler.z1c.entity;

import javax.persistence.*;

@Entity
@Table(name = "FINANCE_XML_PAYMENTS_TEMP")
public class Load_Payments {
  @Id
  @Column(name = "FIN_XML_PAY_ID", nullable = false)
  @SequenceGenerator(name = "FINANCE_XML_PAYMENTS_TEMP_SEQ", sequenceName = "FINANCE_XML_PAYMENTS_TEMP_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCE_XML_PAYMENTS_TEMP_SEQ")
  public Long fin_xml_pay_id;

  @Column(name = "FIN_XML_BILL_GUID")
  public String fin_xml_bill_guid;

  @Column(name = "FIN_XML_PAY_DATE")
  public String fin_xml_pay_date;

  @Column(name = "FIN_XML_PAY_CUR")
  public String fin_xml_pay_cur;

  @Column(name = "FIN_XML_PAY_CUR_SUM")
  public String fin_xml_pay_cur_sum;

  public Long getFin_xml_pay_id() {
    return fin_xml_pay_id;
  }

  public void setFin_xml_pay_id(Long fin_xml_pay_id) {
    this.fin_xml_pay_id = fin_xml_pay_id;
  }

  public String getFin_xml_bill_guid() {
    return fin_xml_bill_guid;
  }

  public void setFin_xml_bill_guid(String fin_xml_bill_guid) {
    this.fin_xml_bill_guid = fin_xml_bill_guid;
  }

  public String getFin_xml_pay_date() {
    return fin_xml_pay_date;
  }

  public void setFin_xml_pay_date(String fin_xml_pay_date) {
    this.fin_xml_pay_date = fin_xml_pay_date;
  }

  public String getFin_xml_pay_cur() {
    return fin_xml_pay_cur;
  }

  public void setFin_xml_pay_cur(String fin_xml_pay_cur) {
    this.fin_xml_pay_cur = fin_xml_pay_cur;
  }

  public String getFin_xml_pay_cur_sum() {
    return fin_xml_pay_cur_sum;
  }

  public void setFin_xml_pay_cur_sum(String fin_xml_pay_cur_sum) {
    this.fin_xml_pay_cur_sum = fin_xml_pay_cur_sum;
  }
}
