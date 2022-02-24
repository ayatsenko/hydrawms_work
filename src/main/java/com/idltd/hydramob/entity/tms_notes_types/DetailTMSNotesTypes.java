package com.idltd.hydramob.entity.tms_notes_types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailTMSNotesTypes {
    @Id
    @Column(name = "CLM_NOTES_TYPE_ID", nullable = false)
    public Long clm_notes_type_id;

    @Column(name = "CLM_NOTES_TYPE_NAME", nullable = false)
    public String clm_notes_type_name;

    @Column(name = "CLM_NOTES_TYPE_SNAME")
    public String clm_notes_type_sname;

    @Column(name = "CLM_NOTES_TYPE_DESCRIPTION")
    public String clm_notes_type_description;

    @Column(name = "CLM_NOTES_TYPE_COLOUR")
    public String clm_notes_type_colour;
}
