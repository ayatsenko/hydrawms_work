package com.idltd.hydramob.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TELEGRAMTASK")
public class TelegramTask {

  @Id
  @Column(name = "TT_ID", nullable = false)
  @SequenceGenerator(name = "TELEGRAMTASK_SEQ", sequenceName = "TELEGRAMTASK_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TELEGRAMTASK_SEQ")
  public Long tt_id;

  @Column(name = "TT_CHATID")
  public Long tt_chatid;

  @Column(name = "TT_TEXT", nullable = false)
  public String tt_text	;

  @Column(name = "TT_CREATED")
  public Date tt_created;

  @Column(name = "TT_DONE")
  public Date tt_done;

  @Column(name = "TT_STATE")
  public String tt_state;

  @Column(name = "TT_ERROR")
  public String tt_error;
}
