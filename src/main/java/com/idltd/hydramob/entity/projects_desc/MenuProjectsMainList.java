package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuProjectsMainList {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "PR_ID", nullable = false)
    public Long pr_id;

    @Column(name = "PARENT_PR_ID", nullable = false)
    public Long parent_pr_id;

    @Column(name = "PR_SNAME", nullable = false)
    public String pr_sname;

    @Column(name = "PR_TYPE_ID")
    public Long pr_type_id;

    @Column(name = "PR_TYPE_SNAME")
    public String pr_type_sname;

    @Column(name = "PR_TYPE_COLOUR")
    public String pr_type_colour;

    @Column(name = "PR_STATUS_ID")
    public Long pr_status_id;

    @Column(name = "PR_STATUS_SNAME")
    public String pr_status_sname;

    @Column(name = "PR_STATUS_COLOUR")
    public String pr_status_colour;

    @Column(name = "PR_PRIOR_ID")
    public Long pr_prior_id;

    @Column(name = "PR_PRIOR_SNAME")
    public String pr_prior_sname;

    @Column(name = "PR_PRIOR_COLOUR")
    public String pr_prior_colour;

    @Column(name = "PR_ACTION_ID")
    public Long pr_action_id;
}
