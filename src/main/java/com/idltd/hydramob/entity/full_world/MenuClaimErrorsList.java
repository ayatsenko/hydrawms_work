package com.idltd.hydramob.entity.full_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimErrorsList {
    @Id
    @Column(name = "CLM_ERROR_LINK_ID", nullable = false)
    public Long clm_error_link_id;

    @Column(name = "CLM_ERROR_TYPE_ID")
    public Long clm_error_type_id;

    @Column(name = "CLM_ERROR_TYPE_SNAME")
    public String clm_error_type_sname;

    @Column(name = "CLM_ERROR_TYPE_COLOUR")
    public String clm_error_type_colour;

    @Column(name = "CLM_ERROR_LINK_CREATE_DATE")
    public String clm_error_link_create_date;

    @Column(name = "CLM_ERROR_LINK_CREATE_USER_ID")
    public Long clm_error_link_create_user_id;

    @Column(name = "CLM_ERROR_LINK_CREATE_USER_NAME")
    public String clm_error_link_create_user_name;

    @Column(name = "CLM_ERROR_LINK_CLOSE_DATE")
    public String clm_error_link_close_date;

    @Column(name = "CLM_ERROR_LINK_CLOSE_USER_ID")
    public Long clm_error_link_close_user_id;

    @Column(name = "CLM_ERROR_LINK_CLOSE_USER_NAME")
    public String clm_error_link_close_user_name;

    @Column(name = "CLM_ERROR_LINK_CLOSE_ID")
    public Long clm_error_link_close_id;

    @Column(name = "CLM_ERROR_LINK_CLOSE_COLOR")
    public String clm_error_link_close_color;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
