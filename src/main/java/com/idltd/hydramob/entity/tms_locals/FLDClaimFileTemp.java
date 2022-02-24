package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "TMS_CLAIMS_FLD_FILE_TEMP")
public class FLDClaimFileTemp {
    @Id
    @SequenceGenerator(name = "TMS_CLAIMS_FLD_FILE_TEMP_SEQ", sequenceName = "TMS_CLAIMS_FLD_FILE_TEMP_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TMS_CLAIMS_FLD_FILE_TEMP_SEQ")
    public Long clm_fld_file_id;

    @Column(name = "DB_USER_ID", nullable = false)
    public Long db_user_id;

    @Column(name = "DB_ROLE_ID", nullable = false)
    public Long db_role_id;

    @Lob
    @Column(name = "CLM_FLD_FILE_BODY", nullable = false)
    public Blob clm_fld_file_body;

    @Column(name = "CLM_FLD_FILE_CONTENTTYPE", nullable = false)
    public String clm_fld_file_contenttype;
}
