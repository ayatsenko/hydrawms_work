package com.idltd.hydramob.entity.sheduler;

import javax.persistence.*;

@Entity
@Table(name = "SHEDULER_FILE_ARCH")
public class ShedulerFileArch {

    @Id
    @Column(name = "SFA_ID", nullable = false)
    @SequenceGenerator(name = "SHEDULER_FILE_ARCH_SEQ", sequenceName = "SHEDULER_FILE_ARCH_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHEDULER_FILE_ARCH_SEQ")
    public Long sfa_id;

    @Column(name = "SE_ID", nullable = false)
    public Long se_id;

    @Column(name = "SFA_FILE_NAME", nullable = false)
    public String sfa_file_name;

    @Column(name = "SFA_FILE_CONTENTTYPE", nullable = false)
    public String sfa_file_contenttype;

    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "SFA_FILE_BODY", nullable = false)
    public String sfa_file_body;

    @Column(name = "SFA_COLUMNSEPARATOR", nullable = false)
    public Character sfa_columnseparator;

    @Column(name = "SFA_TYPE_ID", nullable = false)
    public Long sfa_type_id;

    @Column(name = "SFA_TYPE_INSERT_ID", nullable = false)
    public Long sfa_type_insert_id;
}
