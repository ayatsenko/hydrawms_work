package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name = "SHEDULER_BOT")
public class Sheduler_Bot {

  @Id
  @Column(name = "SB_ID", nullable = false)
  @SequenceGenerator(name = "SHEDULER_BOT_SEQ", sequenceName = "SHEDULER_BOT_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHEDULER_BOT_SEQ")
  public Long sb_id;

  @Column(name = "SET_ID")
  public Long set_id;

  @Column(name = "TRC_SHIPMENT", nullable = false)
  public String trc_shipment;

  @Column(name = "SB_STATUS")
  public Long sb_status;

  @Column(name = "SB_TELEGRAMID")
  public Long sb_telegramid;

}
