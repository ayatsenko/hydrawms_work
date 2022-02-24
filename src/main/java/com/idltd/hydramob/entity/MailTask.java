package com.idltd.hydramob.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MAILTASK")
public class MailTask {

  @Id
  @Column(name = "MT_ID", nullable = false)
  @SequenceGenerator(name = "MAILTASK_SEQ", sequenceName = "MAILTASK_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAILTASK_SEQ")
  public Long mt_id;

  @Column(name = "MT_FROM")
  public String mt_from;

  @Column(name = "MT_TO")
  public String mt_to;

  @Column(name = "MT_SUBJECT")
  public String mt_subject;

  @Column(name = "MT_TEXT", nullable = false)
  public String mt_text	;

  @Column(name = "MT_CREATED")
  public Date mt_created;

  @Column(name = "MT_DONE")
  public Date mt_done;

  @Column(name = "MT_STATE")
  public String mt_state;

  @Column(name = "MT_ERROR")
  public String mt_error;

}
