package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_ROLES")
public class ProjectRolesList {
    @Id
    public Long pr_role_id;

    @Column(name = "PR_ROLE_NAME", nullable = false)
    public String pr_role_name;

    @Column(name = "PR_ROLE_SNAME", nullable = false)
    public String pr_role_sname;

    @Column(name = "PR_ROLE_DESCRIPTION", nullable = false)
    public String pr_role_description;

    @Column(name = "PR_ROLE_COLOUR", nullable = false)
    public String pr_role_colour;
}