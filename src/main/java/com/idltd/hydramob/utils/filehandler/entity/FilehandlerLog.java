package com.idltd.hydramob.utils.filehandler.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "FILEHANDLER_LOG")
public class FilehandlerLog {

  @Id
  @Column(name = "FHL_ID", nullable = false)
  @SequenceGenerator(name = "FILEHANDLER_LOG_SEQ", sequenceName = "FILEHANDLER_LOG_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILEHANDLER_LOG_SEQ")
  private Long fhl_id;
  @Column(name = "FHL_USER")
  private String fhl_user;
  @Column(name = "FHL_STARTDATE")
  private Date fhl_startdate;
  @Column(name = "FHL_ENDDATE")
  private Date fhl_enddate;
  @Column(name = "FHL_STATUS")
  private String fhl_status;
  @Column(name = "FHL_BODY")
  private String fhl_body;
  @Column(name = "FHL_ERRORBODY")
  private String fhl_errorbody;

  public Long getFhl_id() {
    return fhl_id;
  }

  public void setFhl_id(Long fhl_id) {
    this.fhl_id = fhl_id;
  }

  public String getFhl_user() {
    return fhl_user;
  }

  public void setFhl_user(String fhl_user) {
    this.fhl_user = fhl_user;
  }

  public Date getFhl_startdate() {
    return fhl_startdate;
  }

  public void setFhl_startdate(Date fhl_startdate) {
    this.fhl_startdate = fhl_startdate;
  }

  public Date getFhl_enddate() {
    return fhl_enddate;
  }

  public void setFhl_enddate(Date fhl_enddate) {
    this.fhl_enddate = fhl_enddate;
  }

  public String getFhl_status() {
    return fhl_status;
  }

  public void setFhl_status(String fhl_status) {
    this.fhl_status = fhl_status;
  }

  public String getFhl_body() {
    return fhl_body;
  }

  public void setFhl_body(String fhl_body) {
    this.fhl_body = fhl_body;
  }

  public String getFhl_errorbody() {
    return fhl_errorbody;
  }

  public void setFhl_errorbody(String fhl_errorbody) {
    this.fhl_errorbody = fhl_errorbody;
  }
}
