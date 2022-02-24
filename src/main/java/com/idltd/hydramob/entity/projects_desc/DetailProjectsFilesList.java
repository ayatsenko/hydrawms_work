package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailProjectsFilesList {
    @Id
    @Column(name = "PR_FILE_ID", nullable = false)
    public Long pr_file_id;

    @Column(name = "PR_ID", nullable = false)
    public Long pr_id;

    @Column(name = "PR_FILE_NAME")
    public String pr_file_name;

    @Column(name = "PR_FILE_DATE")
    public String pr_file_date;

    @Column(name = "PR_FILE_CONTENTTYPE")
    public String pr_file_contenttype;

    @Column(name = "USER_ID")
    public String user_id;
}
