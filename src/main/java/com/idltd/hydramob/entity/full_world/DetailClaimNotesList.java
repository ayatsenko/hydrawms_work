package com.idltd.hydramob.entity.full_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailClaimNotesList {
    @Id
    @Column(name = "CLMNL_ID", nullable = false)
    public Long clmnl_id;

    @Column(name = "CLM_NOTES_TYPE_ID")
    public Long clm_notes_type_id;

    @Column(name = "CLMNL_TEXT")
    public String clmnl_text;
}
