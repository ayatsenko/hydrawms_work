package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailProjectsChatList {
    @Id
    @Column(name = "PR_CHAT_ID", nullable = false)
    public Long pr_chat_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "PR_CHAT_TEXT")
    public String pr_chat_text;
}
