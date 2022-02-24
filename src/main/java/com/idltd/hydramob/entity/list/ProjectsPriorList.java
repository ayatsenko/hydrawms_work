package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_PRIOR")
public class ProjectsPriorList {
    @Id
    @Column(name = "PR_PRIOR_ID", nullable = false)
    public Long pr_prior_id;

    @Column(name = "PR_PRIOR_NAME")
    public String pr_prior_name;

    @Column(name = "PR_PRIOR_SNAME")
    public String pr_prior_sname;
}
