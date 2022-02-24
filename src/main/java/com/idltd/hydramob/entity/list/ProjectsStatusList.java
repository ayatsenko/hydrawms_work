package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_STATUS")
public class ProjectsStatusList {
    @Id
    @Column(name = "PR_STATUS_ID", nullable = false)
    public Long pr_status_id;

    @Column(name = "PR_STATUS_NAME")
    public String pr_status_name;

    @Column(name = "PR_STATUS_SNAME")
    public String pr_status_sname;
}
