package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuProjectsUsersList {
    @Id
    @Column(name = "PRRL_ID", nullable = false)
    public Long prrl_id;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "PR_ROLE_ID")
    public Long pr_role_id;

    @Column(name = "PR_ROLE_SNAME")
    public String pr_role_sname;

    @Column(name = "PRRL_CREATE_DATE")
    public String prrl_create_date;
}
