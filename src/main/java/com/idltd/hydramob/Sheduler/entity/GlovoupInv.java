package com.idltd.hydramob.Sheduler.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GlovoupInv")
public class GlovoupInv {

  @Id
  @Column(name = "GUI_ID", nullable = false)
  @SequenceGenerator(name = "GlovoupInv_SEQ", sequenceName = "GlovoupInv_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GlovoupInv_SEQ")
  public Long gui_id;

  @Column(name = "GUI_DATE")
  public Date gui_date;

  @Column(name = "GUI_SKU")
  public String gui_sku;

  @Column(name = "GUI_QVN")
  public Long gui_qvn;

}
