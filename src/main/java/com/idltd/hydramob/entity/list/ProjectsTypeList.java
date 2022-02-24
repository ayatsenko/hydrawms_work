package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_TYPE")
public class ProjectsTypeList {
    @Id
    @Column(name = "PR_TYPE_ID", nullable = false)
    public Long pr_type_id;

    @Column(name = "PR_TYPE_NAME")
    public String pr_type_name;

    @Column(name = "PR_TYPE_SNAME")
    public String pr_type_sname;
}
