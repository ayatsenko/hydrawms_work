package com.idltd.hydramob.entity.requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuRequestChatList {
    @Id
    @Column(name = "REQ_CHAT_ID", nullable = false)
    public Long req_chat_id;

    @Column(name = "REQ_CHAT_DATE")
    public String req_chat_date;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "REQ_CHAT_TEXT")
    public String req_chat_text;

    @Column(name = "REQ_CHAT_TEXT_SH")
    public String req_chat_text_sh;
}
