package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailProjectsUsersList {
    @Id
    @Column(name = "PRRL_ID", nullable = false)
    public Long prrl_id;

    @Column(name = "PR_ROLE_ID")
    public Long pr_role_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "PRRL_PARTNER_ID")
    public Long prrl_partner_id;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CC_ID")
    public Long cc_id;

    @Column(name = "PRRL_EMPLOYEE_ID")
    public Long prrl_employee_id;

    @Column(name = "PRRL_DESCRIPTION")
    public String prrl_description;
}
