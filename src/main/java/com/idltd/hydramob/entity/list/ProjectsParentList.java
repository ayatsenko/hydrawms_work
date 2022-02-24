package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProjectsParentList {
    @Id
    @Column(name = "PARENT_PR_ID", nullable = false)
    public Long parent_pr_id;

    @Column(name = "PARENT_PR_NAME")
    public String parent_pr_name;
}
