package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuProjectsChatList {
    @Id
    @Column(name = "PR_CHAT_ID", nullable = false)
    public Long pr_chat_id;

    @Column(name = "PR_CHAT_CREATE_DATE", nullable = false)
    public String pr_chat_create_date;

    @Column(name = "EDIT_CHECK_ID", nullable = false)
    public Long edit_check_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "PR_CHAT_TEXT")
    public String pr_chat_text;

    @Column(name = "PR_CHAT_EDIT_DATE")
    public String pr_chat_edit_date;

    @Column(name = "PR_CHAT_DELETE_DATE")
    public String pr_chat_delete_date;
}
