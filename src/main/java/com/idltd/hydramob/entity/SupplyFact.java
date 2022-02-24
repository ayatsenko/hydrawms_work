package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLY_FACT")
public class SupplyFact {

  @Id
  @Column(name = "SFC_ID", nullable = false)
  @SequenceGenerator(name = "SUPPLY_FACT_SEQ", sequenceName = "SUPPLY_FACT_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_FACT_SEQ")
  public Long sfc_id;

  @Column(name = "SFC_BODY", nullable = false)
  public String sfc_body;

  @Column(name = "SP_ID", nullable = false)
  public Long sp_id;


}
