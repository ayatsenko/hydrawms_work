package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CLAIMS_NOTES_TYPES")
public class TMS_Notes_Types_List {
    @Id
    public Long clm_notes_type_id;

    @Column(name = "CLM_NOTES_TYPE_NAME", nullable = false)
    public String clm_notes_type_name;

    @Column(name = "CLM_NOTES_TYPE_SNAME", nullable = false)
    public String clm_notes_type_sname;

    @Column(name = "CLM_NOTES_TYPE_DESCRIPTION", nullable = false)
    public String clm_notes_type_description;

    @Column(name = "CLM_NOTES_TYPE_COLOUR", nullable = false)
    public String clm_notes_type_colour;

    @Column(name = "VT_ID", nullable = false)
    public Long vt_id;
}