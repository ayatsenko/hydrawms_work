package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name = "REQUEST_FILE")
public class Request_file {

  @Id
  @Column(name = "RF_ID", nullable = false)
  @SequenceGenerator(name = "REQUEST_FILE_SEQ", sequenceName = "REQUEST_FILE_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_FILE_SEQ")
  public Long rf_id;

  @Column(name = "RF_NAME")
  public String rf_name;

  @Column(name = "RF_TYPE")
  public String rf_type;

  @Lob
  @Column(name = "RF_BODY")
  public byte[] rf_body;

  @Column(name = "REQ_ID")
  public  Long req_id;

}
