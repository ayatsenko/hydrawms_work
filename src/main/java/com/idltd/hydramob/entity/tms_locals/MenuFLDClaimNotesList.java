package com.idltd.hydramob.entity.tms_locals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLDClaimNotesList {
    @Id
    @Column(name = "CLMNL_ID", nullable = false)
    public Long clmnl_id;

    @Column(name = "CLM_NOTES_TYPE_ID")
    public Long clm_notes_type_id;

    @Column(name = "CLM_NOTES_TYPE_NAME")
    public String clm_notes_type_name;

    @Column(name = "CLM_NOTES_TYPE_COLOUR")
    public String clm_notes_type_colour;

    @Column(name = "CLMNL_TEXT")
    public String clmnl_text;
}
