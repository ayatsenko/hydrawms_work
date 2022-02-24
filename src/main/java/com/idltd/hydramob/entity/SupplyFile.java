package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLY_FILE")
public class SupplyFile {

  @Id
  @Column(name = "SF_ID", nullable = false)
  @SequenceGenerator(name = "SUPPLY_FILE_SEQ", sequenceName = "SUPPLY_FILE_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_FILE_SEQ")
  public Long sf_id;

  @Column(name = "SF_FILENAME")
  public String sf_filename;

  @Column(name = "SF_BODY")
  public String sf_body;

  @Column(name = "SF_DATE")
  public java.sql.Date sf_date;

  @Column(name = "SP_ID", nullable = false)
  public Long sp_id;

  @Column(name = "SF_MEDIATYPE")
  public String sf_mediatype;
}
