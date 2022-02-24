package com.idltd.hydramob.entity.projects_desc;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "PROJECT_FILES")
public class ProjectFile {
    @Id
    @SequenceGenerator(name = "PROJECT_FILES_SEQ", sequenceName = "PROJECT_FILES_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_FILES_SEQ")
    public Long pr_file_id;

    @Column(name = "PR_ID", nullable = false)
    public Long pr_id;

    @Column(name = "PR_FILE_DATE", nullable = false)
    public Date pr_file_date;

    @Column(name = "PR_FILE_NAME", nullable = false)
    public String pr_file_name;

    @Lob
    @Column(name = "PR_FILE_BODY", nullable = false)
    public Blob pr_file_body;

    @Column(name = "PR_FILE_CONTENTTYPE", nullable = false)
    public String pr_file_contenttype;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;
}
